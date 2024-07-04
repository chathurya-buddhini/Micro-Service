package com.example.user_service.service;



import com.example.user_service.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    Boolean isUserExit(String userId);

    UserDTO getUserById(String userId);

    UserDTO saveUser (UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser(String userId);
}
