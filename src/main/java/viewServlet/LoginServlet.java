package viewServlet;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.MyTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Xenqiao
 * @create 2023/5/14 15:42
 */
@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    UserDTO userDTO = UserDTO.getUserDTO();

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        System.out.println( request );
        // 获取请求体中的原始数据
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            stringBuilder.append(line);
        }
        reader.close();

        String[] parts = stringBuilder.toString().split("(userName=|&userPassword=)", -1);

        String userName = parts[1];
        String password = parts[2];

        password = MyTools.encrypt(password);

        System.out.println("怎么啦？金桥");
        System.out.println("username:" + userName);
        System.out.println("password:" + password);

        UserDAO userDAO = new UserDAOImpl();
        UserDTO userDTO1 = userDAO.findUserDTO(userName);

        // 设置响应内容类型为JSON格式
        response.setContentType("application/json;charset=utf-8");
        // 获取输出流
        PrintWriter out = response.getWriter();

        if ("".equals(userDTO1.getUserName()) || userDTO1.getUserName() == null) {
            System.out.println("失败");
            // 输出JSON格式的成功响应
            out.print("{\"success\": false,\"message\":\"用户不存在或者密码错误\"}");
        } else if (userDTO1.getPwd().equals(password)) {
            System.out.println("成功");
            // 输出JSON格式的成功响应
            userDTO.setUserName(userDTO1.getUserName());
            userDTO.setPwd(userDTO1.getPwd());
            userDTO.setHash(userDTO1.getHash());

            out.print("{\"success\": true}");
        }
        out.flush(); // 刷新输出流
        out.close(); // 关闭输出流

    }

}
