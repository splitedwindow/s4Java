package com.roman.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class chef {
    @Id
    @GeneratedValue
    private long chefId;
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
    @Column String password;
}