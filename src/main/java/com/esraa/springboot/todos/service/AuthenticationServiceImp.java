package com.esraa.springboot.todos.service;

import com.esraa.springboot.todos.entities.Authority;
import com.esraa.springboot.todos.entities.User;
import com.esraa.springboot.todos.repository.UserRepository;
import com.esraa.springboot.todos.request.RegisterRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceImp implements AuthenticationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthenticationServiceImp(UserRepository userRepository,PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    @Override
    public void register(RegisterRequest input) throws Exception {
        if(isEmailTaken(input.getEmail())){
            throw new Exception("Email is already taken");
        }
        User user = buildNewUser(input);
        userRepository.save(user);
    }
    private boolean isEmailTaken(String email){
        return userRepository.findByEmail(email).isPresent();
    }
    private User buildNewUser(RegisterRequest input){
        User user = new User();
        user.setId(0);
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setAuthorities(intialAuthority());
        return user;
    }
    private List<Authority>intialAuthority(){
        boolean isFirstUser=userRepository.count()==0;
        List<Authority>authorities=new ArrayList<>();
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        if(isFirstUser){
            authorities.add(new Authority("ROLE_ADMIN"));
        }
        return authorities;
    }
}
