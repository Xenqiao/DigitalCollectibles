package viewServlet; /**
 * @author Xenqiao
 * @create 2023/5/21 23:44
 */

import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.util.Base64.*;

@WebServlet(name = "UploadImgServlet", value = "/UploadImgServlet")
public class UploadImgServlet extends HttpServlet {
    UserDTO userDTO = UserDTO.getUserDTO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = userDTO.getName();
        String userName = userDTO.getUserName();
        String hash = userDTO.getHash();
        String balance = String.valueOf(userDTO.getBalance());
        StringBuilder pictureSource = new StringBuilder();
        pictureSource.append("jsp/"+userDTO.getUserName()+".jpg");

        // 将多个参数打包成一个 JSON 对象
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("userName", userName);
            jsonObject.put("hash", hash);
            jsonObject.put("balance", balance);
            jsonObject.put("pictureSource", pictureSource);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // 将 JSON 对象作为响应数据返回
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");


        String photoData = request.getParameter("photo");
        byte[] photoBytes = getDecoder().decode(photoData);

        // 保存图片数据到文件

        File uploadDir = new File("C:\\Users\\谢金桥\\Desktop\\luanLai\\src\\main\\webapp\\", "jpg");
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        StringBuilder filename = new StringBuilder();
        filename.append( userDTO.getUserName() + ".jpg");
        File file = new File(uploadDir, filename.toString());

        if (file.exists()) {
            file.delete();
            System.out.println("文件存在吗？");
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bos.write(photoBytes);
        bos.flush();
        bos.close();

        // 发送响应到客户端
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("这是什么呢？" + "jpg/" + filename);
        response.getWriter().print("{\"success\": true,\"message\":\""+ "jpg/" + filename +"\"}");
    }
}
