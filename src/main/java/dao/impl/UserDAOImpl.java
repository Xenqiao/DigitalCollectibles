package dao.impl;

import dao.DBUtil;
import dao.MysqlDAO;
import dao.MySqlBuilder;
import dao.UserDAO;
import dto.UserDTO;
import service.CreateUserService;
import service.impl.CreateUserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Xenqiao
 * @create 2023/5/14 19:07
 */
public class UserDAOImpl implements UserDAO {
    private Connection conn;
    private PreparedStatement ps;
    private String sql;
    private ResultSet rs;

    UserDTO userDTO = UserDTO.getUserDTO();

    @Override
    /**
     * 登录时找到对应用户的数据
     **/
    public UserDTO findUserDTO(String userName) {
        conn = DBUtil.getConn();
        if (conn == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        try {
            MySqlBuilder mySqlBuilder = new MySqlBuilder();
            String[] strings = {"*"};
            sql = mySqlBuilder.select(strings,"user","userName");

            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {

                userDTO.setUserName(rs.getString("userName"));
                userDTO.setPwd(rs.getString("password"));
                userDTO.setHash(rs.getString("hash"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBUtil.closeAll(conn, ps, rs);
        return userDTO;
    }

    /** 创建新的用户 **/
    @Override
    public boolean registerUser(){

        CreateUserService createUserService = new CreateUserServiceImpl();
        String hash = createUserService.getUserHash();
        userDTO.setHash(hash);

        //sql = " insert into user(userName,password,hash) values(?,?,?) ";
        MySqlBuilder mySqlBuilder = new MySqlBuilder();
        String[] strings = {"userName","password","hash"};
        sql = mySqlBuilder.insert("user",strings);

        Object[] param = {
                userDTO.getUserName(),
                userDTO.getPwd(),
                userDTO.getHash()
        };
        MysqlDAO mysqlDAO = new MysqlDAO();
        return mysqlDAO.executeUpdate(sql,param);
    }

    @Override
    public void addCustomer(UserDTO userDTO) {

    }

}
