package com.example;

import com.example.env.SimpleTransactionManagerTest;
import com.example.model.Bid;
import com.example.model.Item;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderColumnTest extends SimpleTransactionManagerTest {

    @BeforeClass
    public static void init() {
        initEntityManagerFactory("ordercolumn");
    }

    @Test
    public void test() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            Item item1 = new Item("Sony Xperia Z3 Compact");
            new Bid(item1, "4000");
            new Bid(item1, "4500");
            new Bid(item1, "4800");
            em.persist(item1);

            Item item2 = new Item("Xiaomi Redmi Note 7");
            new Bid(item2, "6000");
            new Bid(item2, "6100");
            new Bid(item2, "6300");
            new Bid(item2, "6900");
            em.persist(item2);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }

        em = EMF.createEntityManager();
        try {
            TX.begin();

            List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();

            LOG.info("Items: {}", items);
            for (Item item : items) {
                for (int i = 0; i < item.getBids().size(); i++) {
                    assertEquals(i, item.getBids().get(i).getOrder().intValue());
                }
            }

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }
}
