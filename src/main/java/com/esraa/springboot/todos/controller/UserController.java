package com.esraa.springboot.todos.controller;

import com.esraa.springboot.todos.entities.User;
import com.esraa.springboot.todos.response.UserResponse;
import com.esraa.springboot.todos.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@Tag(name = "User rest api endpoint.", description = "Operations related to current user details.")
@RestController
@RequestMapping("/api/users")
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
    
    @GetMapping("/info")
    public UserResponse getUserInfo() throws AccessDeniedException {
        return userService.getUserInfo();
    }

    @DeleteMapping
    public void deleteUser(){
        userService.deleteUser();
    }
}
