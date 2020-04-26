package com.example.model.simple;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Item {

    protected Set<Bid> bids = new HashSet<>();

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        Objects.requireNonNull(bid, "Can't add null Bid");
        if (bid.getItem() != null) {
            throw new IllegalStateException("Bid is already assigned to an Item");
        }

        bids.add(bid);
        bid.item = this;
    }

    public void removeBid(Bid bid) {
        Objects.requireNonNull(bid, "Can't remove null Bid");
        if (bid.getItem() != this) {
            throw new IllegalStateException("Bid is already removed to an Item");
        }

        bids.remove(bid);
        bid.item = null;
    }
}
