package com.example.model.generatedvalue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class NameSequence {
    @Id
    @GeneratedValue(generator = "SEQUENCE_GENERATOR")
    // Hibernate global scope не поддерживает, хотя JPA обязывает это
    @SequenceGenerator(name = "SEQUENCE_GENERATOR", sequenceName = "custom_seq_generator")
    protected Long id;
    protected String value;

    public NameSequence() {
    }
    public NameSequence(String value) {
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
        return "NameSequence{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
