package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String value;

    public Auto() {
    }
    public Auto(String value) {
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
        return "Auto{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
