package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UUID {
    @Id
    @GeneratedValue(generator = "UUID_GENERATOR")
    protected java.util.UUID id;
    protected String value;

    public java.util.UUID getId() {
        return id;
    }
    public String getValue() {
        return value;
    }

    public UUID() {
    }
    public UUID(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UUID{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
