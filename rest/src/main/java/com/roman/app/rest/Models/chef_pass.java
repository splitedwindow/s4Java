package com.roman.app.rest.Models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Entity
public class chef_pass {
    @Id
    @GeneratedValue
    private long id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column String password;
}
