package com.roman.app.rest.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class orders {
    @Id
    @GeneratedValue
    private long orderId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column
    private long userId;

    public long getDishId() {
        return dish_id;
    }

    public void setDishId(int id) {
        this.dish_id = id;
    }

    @Column
    private long dish_id;
}
