package com.example.model.subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Bid {
    @Id
    @GeneratedValue
    protected Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    protected Item item;
    @Column(nullable = false)
    protected BigDecimal amount;

    public Bid() {
    }
    public Bid(Item item, BigDecimal amount) {
        this.item = item;
        item.getBids().add(this);
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
