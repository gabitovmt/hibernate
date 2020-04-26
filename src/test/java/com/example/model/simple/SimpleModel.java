package com.example.model.simple;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

public class SimpleModel {

    @Test
    public void test() {
        Item item = new Item();
        Bid bid1 = new Bid(item);

        assertSame(bid1.getItem(), item);
        assertTrue(item.getBids().contains(bid1));
        assertEquals(item.getBids().size(), 1);

        Bid bid2 = new Bid();
        item.addBid(bid2);

        assertSame(bid2.getItem(), item);
        assertTrue(item.getBids().contains(bid2));
        assertEquals(item.getBids().size(), 2);

        item.removeBid(bid1);

        assertNull(bid1.getItem());
        assertFalse(item.getBids().contains(bid1));
        assertEquals(item.getBids().size(), 1);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testModify() {
        Item item = new Item();
        item.getBids().add(new Bid());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addNull() {
        new Item().addBid(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeNull() {
        new Item().removeBid(null);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void itemNonNull() {
        Item item1 = new Item();
        Item item2 = new Item();
        Bid bid = new Bid(item1);

        item2.addBid(bid);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void itemIsNull() {
        Item item = new Item();
        item.removeBid(new Bid());
    }
}
