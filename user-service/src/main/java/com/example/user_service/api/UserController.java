package com.example.user_service.api;


import com.example.user_service.dto.UserDTO;
import com.example.user_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUser() {

        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public UserDTO getUserByID(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "isExit/{id}")
    public Boolean isUserExit(@PathVariable("id") String id){
        return userService.isUserExit(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        return userService.saveUser(userDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }




}
//{
//        "id": "U002",
//        "username": "kamal",
//        "email": "kamal@gmail.com",
//        "password": "1234",
//        "role": "ADMIN",
//        "phone": "0716077881"
//        }