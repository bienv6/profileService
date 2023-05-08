package com.upwork.DemoProject.ProfileData.dto;

import com.upwork.DemoProject.ProfileData.Entity.AuthRecord;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProfileRequestDto {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;

    private String address;

    @NotEmpty
    @Email(message = "Email should be in proper format")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "mobNo should have at least 10 characters")
    private String mobNo;

    private String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    private AuthRecordRequestDto authRecord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public AuthRecordRequestDto getAuthRecord() {
        return authRecord;
    }

    public void setAuthRecord(AuthRecordRequestDto authRecord) {
        this.authRecord = authRecord;
    }
}
