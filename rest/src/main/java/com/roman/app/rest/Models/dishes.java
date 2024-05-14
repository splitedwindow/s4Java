package com.roman.app.rest.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class dishes {
    @Id
    @GeneratedValue
    private long dishId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String name;
}
