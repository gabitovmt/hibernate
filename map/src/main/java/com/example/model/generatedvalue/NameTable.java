package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class NameTable {
    @Id
    @GeneratedValue(generator = "TABLE_GENERATOR")
    // Hibernate global scope не поддерживает, хотя JPA обязывает это
    @TableGenerator(name = "TABLE_GENERATOR", table = "custom_table_generator")
    protected Long id;
    protected String value;

    public NameTable() {
    }
    public NameTable(String value) {
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
        return "NameTable{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
