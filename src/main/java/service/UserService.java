package service;

import dto.UserDTO;

import java.util.List;

/**
 * @author Xenqiao
 * @create 2023/4/27 21:34
 */
public interface UserService {
    List<UserDTO> findUserExists(String userName);
    int size();
}
