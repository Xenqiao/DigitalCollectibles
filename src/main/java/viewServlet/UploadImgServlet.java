package viewServlet; /**
 * @author Xenqiao
 * @create 2023/5/21 23:44
 */

import dto.MyLoggerDTO;
import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

import java.util.logging.Level;


@WebServlet(name = "UploadImgServlet", value = "/UServlet")
@MultipartConfig
public class UploadImgServlet extends HttpServlet {
    UserDTO userDTO = UserDTO.getUserDTO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = userDTO.getName();
        String userName = userDTO.getUserName();
        String hash = userDTO.getHash();
        String balance = String.valueOf(userDTO.getBalance());
        StringBuilder pictureSource = new StringBuilder();
        pictureSource.append("jpg/").append(userDTO.getUserName()).append(".jpg");


        // 将多个参数打包成一个 JSON 对象
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("userName", userName);
            jsonObject.put("hash", hash);
            jsonObject.put("balance", balance);
            jsonObject.put("pictureSource", pictureSource);

            File file = new File("C:\\Users\\谢金桥\\Desktop\\uu\\src\\main\\webapp\\", pictureSource.toString());
            if (file.exists()){
                jsonObject.put("message", "1");
            }else {
                jsonObject.put("message", "0");
            }
        } catch (JSONException e) {
            MyLoggerDTO.getMyLoggerDTO().log(
                    Level.SEVERE,
                    "（返回给html页面的JSON对象封装失败）UploadImgServlet"
            );
            throw new RuntimeException(e);
        }

        // 将 JSON 对象作为响应数据返回
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取上传的文件
        Part filePart = request.getPart("image");
        System.out.println("有什么问题吗？");
        // 将文件保存到webapp/jpg目录下
        File uploadDir = new File("C:\\Users\\谢金桥\\Desktop\\uu\\src\\main\\webapp\\", "jpg");
        if (!uploadDir.exists()) {
            boolean file = uploadDir.mkdir();
            System.out.println("文件夹存在吗？" + file);
        }
        StringBuilder pictureSource = new StringBuilder();

        pictureSource.append(userDTO.getUserName()).append(".jpg");
        File file = new File(uploadDir, pictureSource.toString());
        if (file.exists()) {
            boolean a = file.delete();
            System.out.println("文件存在吗？"+ a);
        }
        pictureSource.setLength(0);
        pictureSource.append("C:\\Users\\谢金桥\\Desktop\\uu\\src\\main\\webapp\\jpg\\").append(userDTO.getUserName()).append(".jpg");
        filePart.write(pictureSource.toString());

        // 返回响应给客户端
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        if (file.exists()) {
            response.setStatus(HttpServletResponse.SC_OK);
            System.out.println("上传成功！");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println("上传失败！");
        }
    }
}
