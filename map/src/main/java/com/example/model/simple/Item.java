package com.example.model.simple;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    protected Long id;

    public Long getId() {
        return id;
    }
}
