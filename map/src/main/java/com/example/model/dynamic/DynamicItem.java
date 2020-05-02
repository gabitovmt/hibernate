package com.example.model.dynamic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/*
 * Плюсы и минусы динамического создания инструкций вставки и изменения
 * + Быстрый запуск приложения, т.к. не создаются инструкции на старте приложения.
 * + Меньше потребления памяти, т.к. инструкции не хранятся в кэше.
 * + Короткие инструкции, если требуется передавать мало полей.
 * - Потеря времени при каждой генерации SQL-запросов.
 */
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class DynamicItem {
    @Id
    @GeneratedValue
    protected Long id;
    protected String value1;
    protected String value2;

    public Long getId() {
        return id;
    }

    public String getValue1() {
        return value1;
    }
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "DynamicItem{" +
                "id=" + id +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                '}';
    }
}
