package com.example.model.simple;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
    protected String name;

    @Future
    protected Date auctionEnd;

    @Transient
    protected Set<Bid> bids = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", auctionEnd=" + auctionEnd +
                ", bids=" + bids +
                '}';
    }
}
