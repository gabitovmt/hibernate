package com.example.model.simple;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SimpleModel {

    @Test
    public void test() {
        Item item = new Item();
        Bid bid1 = new Bid(item);

        assertSame(bid1.getItem(), item);
        assertTrue(item.getBids().contains(bid1));
        assertEquals(1, item.getBids().size());

        Bid bid2 = new Bid();
        item.addBid(bid2);

        assertSame(bid2.getItem(), item);
        assertTrue(item.getBids().contains(bid2));
        assertEquals(2, item.getBids().size());

        item.removeBid(bid1);

        assertNull(bid1.getItem());
        assertFalse(item.getBids().contains(bid1));
        assertEquals(1, item.getBids().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testModify() {
        Item item = new Item();
        item.getBids().add(new Bid());
    }

    @Test(expected = NullPointerException.class)
    public void addNull() {
        new Item().addBid(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeNull() {
        new Item().removeBid(null);
    }

    @Test(expected = IllegalStateException.class)
    public void itemNonNull() {
        Item item1 = new Item();
        Item item2 = new Item();
        Bid bid = new Bid(item1);

        item2.addBid(bid);
    }

    @Test(expected = IllegalStateException.class)
    public void itemIsNull() {
        Item item = new Item();
        item.removeBid(new Bid());
    }
}
