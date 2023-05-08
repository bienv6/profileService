package com.upwork.DemoProject.ProfileData.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String email;

    private String mobNo;

    private String roles;

    @OneToOne(mappedBy = "personId",cascade = CascadeType.ALL)
    private AuthRecord authRecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public AuthRecord getAuthRecord() {
        return authRecord;
    }

    public void setAuthRecord(AuthRecord authRecord) {
        this.authRecord = authRecord;
    }
}
