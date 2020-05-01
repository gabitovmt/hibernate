package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class UUIDHex {
    @Id
    @GeneratedValue(generator = "UUID_HEX_GENERATOR")
    protected String id;
    protected String value;

    public String getId() {
        return id;
    }
    public String getValue() {
        return value;
    }

    public UUIDHex() {
    }
    public UUIDHex(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UUIDHex{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
