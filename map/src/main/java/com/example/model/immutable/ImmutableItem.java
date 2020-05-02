package com.example.model.immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/*
 * Сообщая Hibernate, что сущность неизменяемая, он делает различные оптимизации.
 */
@org.hibernate.annotations.Immutable
public class ImmutableItem {
    @Id
    @GeneratedValue
    protected Long id;
    protected String value;

    public ImmutableItem() {
    }
    public ImmutableItem(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ImmutableItem{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
