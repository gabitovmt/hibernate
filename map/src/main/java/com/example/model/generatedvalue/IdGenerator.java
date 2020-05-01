package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IdGenerator {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;
    protected String value;

    public IdGenerator() {
    }
    public IdGenerator(String value) {
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
        return "IdGenerator{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
