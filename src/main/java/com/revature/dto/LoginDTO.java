package com.revature.dto;

// DTO stands for Data Transfer Object
// Its purpose is to pass data around in the form of an object
// In this case, we create a class called LoginDTO so that it can pass around username and userpass information
public class LoginDTO {

    private String username;
    private String userpass;

    public LoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }
}