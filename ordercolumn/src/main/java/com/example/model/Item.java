package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue
    protected Long id;
    @Column(nullable = false)
    protected String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "item")
    @OrderColumn(name = "order")
    protected List<Bid> bids = new ArrayList<>();

    public Item() {
    }
    public Item(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Bid> getBids() {
        return bids;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bids=" + bids +
                '}';
    }
}
