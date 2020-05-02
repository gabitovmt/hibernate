package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// Delimited identifier, отделяемый идентификатор в Hibernate
//@javax.persistence.Table(name = "`Table`")
// Delimited identifier, отделяемый идентификатор в JPA
//@javax.persistence.Table(name = "\"Table\"")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;
    protected String value;

    public Table() {
    }
    public Table(String value) {
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
        return "Table{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
