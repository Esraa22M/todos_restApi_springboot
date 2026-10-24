package com.esraa.springboot.todos.controller;

import com.esraa.springboot.todos.entities.User;
import com.esraa.springboot.todos.request.PasswordUpdateRequest;
import com.esraa.springboot.todos.response.UserResponse;
import com.esraa.springboot.todos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@Tag(name = "User rest api endpoint.", description = "Operations related to current user details.")
@RestController
@RequestMapping("/api/users")
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "User Information" , description = "Get current user info")
    @GetMapping("/info")
    public UserResponse getUserInfo() throws AccessDeniedException {
        return userService.getUserInfo();
    }


    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete User" , description = "Delete current user account")
    @DeleteMapping
    public void deleteUser(){
        userService.deleteUser();
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Password Update." , description = "Change user password after verification")
    @PutMapping("/password")
    public void passwordUpdate(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest )throws Exception{
        userService.updatePassword(passwordUpdateRequest);
    }
}
