package com.esraa.springboot.todos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {
    @NotEmpty(message = "Old Password is mandatory")
    @Size(min=5 , max=30 , message = "Password must be at least 5 char long.")
    private String oldPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedpassword() {
        return confirmedpassword;
    }

    public void setConfirmedpassword(String confirmedpassword) {
        this.confirmedpassword = confirmedpassword;
    }

    public PasswordUpdateRequest(String oldPassword, String newPassword, String confirmedpassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmedpassword = confirmedpassword;
    }

    @NotEmpty(message = "New Password is mandatory")
    @Size(min=5 , max=30 , message = "Password must be at least 5 char long.")
    private String newPassword;

    @NotEmpty(message = "Confirmed Password is mandatory")
    @Size(min=5 , max=30 , message = "Confirmed Password must be at least 5 char long.")
    private String confirmedpassword;
}
