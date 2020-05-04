package com.example.model;

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
    @Column
    protected Integer order;

    public Bid() {
    }
    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this);
    }
    public Bid(Item item, String amount) {
        this(item);
        this.amount = new BigDecimal(amount);
    }

    public Long getId() {
        return id;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", amount=" + amount +
                ", order=" + order +
                '}';
    }
}
