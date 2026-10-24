package com.esraa.springboot.todos.service;

import com.esraa.springboot.todos.entities.Authority;
import com.esraa.springboot.todos.entities.User;
import com.esraa.springboot.todos.repository.UserRepository;
import com.esraa.springboot.todos.request.PasswordUpdateRequest;
import com.esraa.springboot.todos.response.UserResponse;
import com.esraa.springboot.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FindAuthenticatedUser findAuthenticatedUser;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, FindAuthenticatedUser findAuthenticatedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserInfo()  {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        return new UserResponse(user.getId(), user.getFirstName()+" "+user.getLastName(),
                user.getEmail() ,user.getAuthorities()
                .stream().map(auth->(Authority)auth).toList());
    }

    @Override
    public void deleteUser() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        //check is it the last admin or not.
        if(isLastAdmin(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN ,
                "Admin cannot delete itself.");
        userRepository.delete(user);
    }
    private boolean isOldPasswordCorrect(String currentPassword , String oldPassword){
        return passwordEncoder.matches(oldPassword ,currentPassword);
    }
    private boolean isNewPasswordConfirmed(String newPassword ,String newPasswordConfirmed){
      return   newPassword.equals(newPasswordConfirmed);
    }
    @Transactional
    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        if(!isOldPasswordCorrect(user.getPassword(), passwordUpdateRequest.getOldPassword())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is not correct!");
        }
        if(!isNewPasswordConfirmed(passwordUpdateRequest.getNewPassword(), passwordUpdateRequest.getConfirmedpassword())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "New Password not match!");

        }
        if(!isNewPasswordDifferent(passwordUpdateRequest.getNewPassword(), passwordUpdateRequest.getOldPassword())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "New Password must be different from the current password!");

        }
        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }
    private boolean isNewPasswordDifferent(String newPassword , String oldPassword){
        return !oldPassword.equals(newPassword);
    }
    private boolean isLastAdmin(User user){
        boolean isAdmin = user.getAuthorities().stream().
                anyMatch(authority->"ROLE_ADMIN".
                        equals(authority.getAuthority()));
        if(isAdmin){
            long adminCount = userRepository.countAdminUsers();
            return adminCount <=1;
        }
        return false;
    }
}
