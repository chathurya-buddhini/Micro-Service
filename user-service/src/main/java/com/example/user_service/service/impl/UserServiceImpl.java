package com.example.user_service.service.impl;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.repo.UserRepo;
import com.example.user_service.service.UserService;
import com.example.user_service.service.exception.DuplicateRecordException;
import com.example.user_service.service.exception.NotFoundException;
import com.example.user_service.service.util.Transformer;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    Transformer transformer;

    @Override
    public List<UserDTO> getAllUsers() {

        List<UserEntity> users = userRepo.findAll();

        if (users.isEmpty()) {
            throw new NotFoundException("No Users Exist");
        }
        return users.stream().map(userEntity -> transformer.fromUserEntity(userEntity)).toList();
    }

    @Override
    public Boolean isUserExit(String userId) {
        return userRepo.existsById(userId);
    }

    @Override
    public UserDTO getUserById(String userId) {

        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("User nic : " + userId + " doesn't exist");
        }
        return transformer.fromUserEntity(userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User nic : " + userId + " doesn't exist")));
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        if (userRepo.existsById(userDTO.getId())) {
            throw new DuplicateRecordException("User id : " + userDTO.getId() + " already exist");
        }
        return transformer.fromUserEntity(userRepo.save(transformer.toUserEntity(userDTO)));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getId())) {
            throw new NotFoundException("User id : " + userDTO.getId() + " doesn't exist");
        }
        userRepo.save(transformer.toUserEntity(userDTO));
    }

    @Override
    public void deleteUser(String userId) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundException("User id : " + userId + " doesn't exist");
        }
        userRepo.deleteById(userId);
    }

}
