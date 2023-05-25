package dao.impl;

import dao.MysqlDAO;
import dao.MySqlBuilder;
import dao.UserDAO;
import dto.UserDTO;
import service.CreateUserService;
import service.impl.CreateUserServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @author Xenqiao
 * @create 2023/5/14 19:07
 */
public class UserDAOImpl implements UserDAO {
    private String sql;

    UserDTO userDTO = UserDTO.getUserDTO();

    @Override
    /**
     * 登录时找到对应用户的数据
     **/
    public UserDTO findUserDTO(String userName) {

        UserDTO userDTO1 = new UserDTO();

        //sql = " select * from user where userName = ? ";
        MySqlBuilder mySqlBuilder = new MySqlBuilder();
        sql = mySqlBuilder.select(null, "user", "userName");

        MysqlDAO mysqlDAO = new MysqlDAO();
        List<Map<String, Object>> list = mysqlDAO.select(sql, userName);
        if (list.size() == 0) {
            return null;
        }

        userDTO1.setUserName((String) list.get(0).get("userName"));
        userDTO1.setPwd((String) list.get(0).get("password"));
        userDTO1.setHash((String) list.get(0).get("hash"));
        userDTO1.setName((String) list.get(0).get("name"));
        String balance = list.get(0).get("balance").toString();
        userDTO1.setBalance(Integer.valueOf(balance));

        System.out.println(userDTO1.getUserName());
        System.out.println(userDTO1.getPwd());
        System.out.println(userDTO1.getHash());
        System.out.println(userDTO1.getName());
        System.out.println(userDTO1.getBalance());
        return userDTO1;
    }

    /**
     * 创建新的用户
     **/
    @Override
    public boolean registerUser() {

        CreateUserService createUserService = new CreateUserServiceImpl();
        String hash = createUserService.getUserHash();
        userDTO.setHash(hash);
        userDTO.setName("未命名用户");

        //sql = " insert into user(userName,password,hash) values(?,?,?) ";
        MySqlBuilder mySqlBuilder = new MySqlBuilder();
        String[] strings = {"userName", "password", "hash","name"};
        sql = mySqlBuilder.insert("user", strings);

        Object[] param = {
                userDTO.getUserName(),
                userDTO.getPwd(),
                userDTO.getHash(),
                userDTO.getName()
        };
        MysqlDAO mysqlDAO = new MysqlDAO();
        return mysqlDAO.insert(sql, param);
    }

    @Override
    public void addCustomer(UserDTO userDTO) {

    }

}
