package com.upwork.DemoProject.ProfileData.dto;

import java.util.Base64;

public class ProfileResponseDtoForJwt {
    private String email;
    private String password;
    private String roles;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
