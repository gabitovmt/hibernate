package com.example.model.simple;

public class Bid {

    protected Item item;

    public Bid() {}

    public Bid(Item item) {
        item.addBid(this);
    }

    public Item getItem() {
        return item;
    }
}
