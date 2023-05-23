package service.impl;

import dao.impl.UserDAOImpl;
import dto.UserDTO;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xenqiao
 * @create 2023/4/27 21:30
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> findUserExists(String userName){
        UserDTO userDTO = new UserDAOImpl().findUserDTO(userName);
        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO);

        return list;
    }

    @Override
    public int size(){
        return 0;
    }


}
