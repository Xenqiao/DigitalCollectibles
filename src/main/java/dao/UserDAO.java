package dao;

import dto.UserDTO;

/**
 * @author Xenqiao
 * @create 2023/5/14 19:08
 */
public interface UserDAO {
    UserDTO findUserDTO(String userName);
    boolean registerUser();
}
