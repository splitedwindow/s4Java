package com.roman.app.rest.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class customer {
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    @Column
    private String email;
    @Column
    private String password;
}
