package com.roman.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class user {
    public user(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public user() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(nullable = false)
    private String role;

    @Id
    @GeneratedValue
    private long userId;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
}
