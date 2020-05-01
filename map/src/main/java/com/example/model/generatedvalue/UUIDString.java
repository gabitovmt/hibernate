package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UUIDString {
    @Id
    @GeneratedValue(generator = "UUID")
    protected String id;
    protected String value;

    public String getId() {
        return id;
    }
    public String getValue() {
        return value;
    }

    public UUIDString() {
    }
    public UUIDString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UUIDString{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
