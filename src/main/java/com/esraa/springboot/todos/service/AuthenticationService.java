package com.esraa.springboot.todos.service;

import com.esraa.springboot.todos.request.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest)throws Exception;


}
