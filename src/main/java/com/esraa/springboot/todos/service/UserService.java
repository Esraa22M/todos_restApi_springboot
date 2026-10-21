package com.esraa.springboot.todos.service;

import com.esraa.springboot.todos.entities.User;

import java.nio.file.AccessDeniedException;

public interface UserService {
    User getUserInfo() throws AccessDeniedException;
}
