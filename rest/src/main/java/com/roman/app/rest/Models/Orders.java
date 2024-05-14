package com.roman.app.rest.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Orders {
    @Id
    @GeneratedValue
    private long orderId;


    public int getName() {
        return dishId;
    }

    public void setName(int name) {
        this.dishId = name;
    }

    @Column
    private int dishId;

}
