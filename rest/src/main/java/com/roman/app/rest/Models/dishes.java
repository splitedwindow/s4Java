package com.roman.app.rest.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
public class dishes {
    @Id
    @GeneratedValue
    private long dishId;

    public dishes(long dishId, String name) {
        this.dishId = dishId;
        this.name = name;
    }

    public dishes() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String name;
}
