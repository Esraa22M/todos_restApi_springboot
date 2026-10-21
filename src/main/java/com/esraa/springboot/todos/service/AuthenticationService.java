package com.esraa.springboot.todos.service;

import com.esraa.springboot.todos.request.AuthenticationRequest;
import com.esraa.springboot.todos.request.RegisterRequest;
import com.esraa.springboot.todos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest)throws Exception;
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

}
