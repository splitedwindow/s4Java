package com.roman.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private boolean isCooking;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsCooking() {
        return isCooking;
    }

    public void setIsCooking(boolean isCooking) {
        this.isCooking = isCooking;
    }
}
