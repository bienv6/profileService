package com.upwork.DemoProject.ProfileData.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_record")
public class AuthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "person_id")
    private Profile personId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getPersonId() {
        return personId;
    }

    public void setPersonId(Profile personId) {
        this.personId = personId;
    }
}


