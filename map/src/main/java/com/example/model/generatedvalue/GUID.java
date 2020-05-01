package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GUID {
    @Id
    @GeneratedValue(generator = "GUID_GENERATOR")
    protected String id;
    protected String value;

    public GUID() {
    }
    public GUID(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "GUID{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
