package com.esraa.springboot.todos.service;

import com.esraa.springboot.todos.entities.User;
import com.esraa.springboot.todos.response.UserResponse;

import java.nio.file.AccessDeniedException;

public interface UserService {
    UserResponse getUserInfo() throws AccessDeniedException;
    void deleteUser();
}
