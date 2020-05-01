package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String value;

    public Identity() {
    }
    public Identity(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
