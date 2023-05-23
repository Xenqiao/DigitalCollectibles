package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import dto.UserDTO;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/** 用于动态代理实现数据库事务
 * @author Xenqiao
 * @create 2023/4/27 21:30
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    public void CustomerServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userDAO.addCustomer(userDTO);
    }


}
