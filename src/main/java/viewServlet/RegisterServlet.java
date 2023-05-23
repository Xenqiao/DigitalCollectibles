package viewServlet;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.contractRealize.FiscoInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 这是一个响应前端网页请求的类
 * @author Xenqiao
 * @create 2023/5/15 19:32
 */
@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserDTO userDTO = UserDTO.getUserDTO();
    private static final long serialVersionUID = 1L;

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hash = userDTO.getHash();          // 假设这是一串字符串

        response.setContentType("text/plain");   // 设置响应类型为纯文本
        response.setCharacterEncoding("UTF-8");  // 设置字符编码为UTF-8
        response.getWriter().write(hash);        // 将字符串发送给客户端

        System.out.println("打印出来了吗？？？？" + hash);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码
        request.setCharacterEncoding("utf-8");

        // 获取请求体中的原始数据
        BufferedReader reader = request.getReader();
        stringBuilder.setLength(0);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            stringBuilder.append(line);
        }
        reader.close();


        String[] parts = stringBuilder.toString().split("(userName=|&userPassword=)", -1);
        String userName = parts[1];
        String password = parts[2];
        System.out.println(userName + "------" + password);
        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO1 = userDAO.findUserDTO(userName);

        // 设置响应内容类型为JSON格式
        response.setContentType("application/json;charset=utf-8");
        // 获取输出流
        PrintWriter out = response.getWriter();
        if (userDTO1.getUserName() != null && !"".equals(userDTO1.getUserName())) {

//            out.print("null");
            out.print("{\"success\": false,\"message\":\"用户名已存在\"}");

        } else {

            userDTO.setUserName(userName);
            userDTO.setPwd(password);
            boolean registered = userDAO.registerUser();
            System.out.println("哈希值来咯："+userDTO.getHash());

            if ( registered ){
                stringBuilder.setLength(0);
                stringBuilder.append("\"{\\\"success\\\": true,\\\"message\\\":\\\"" + userDTO.getHash() + "\\\"}\"");
                out.print(stringBuilder);
            }else {
                stringBuilder.setLength(0);
                stringBuilder.append("\"{\\\"success\\\": false,\\\"message\\\":\\\"" +""+ "\\\"}\"");
                out.print(stringBuilder);
//                out.print("null");
            }
        }
        out.flush(); // 刷新输出流
        out.close(); // 关闭输出流
    }

    public static void main(String[] args) {
        //boolean a = "".equals((String)null);
        System.out.println((String) null);
        System.out.println("");
    }
}
