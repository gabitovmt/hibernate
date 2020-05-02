package com.example.model.subselect;

import com.example.env.SimpleTransactionManagerTest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SubSelectTest extends SimpleTransactionManagerTest {

    @BeforeClass
    public static void init() {
        initEntityManagerFactory("map");
    }

    /**
     * Отображение сущности в подзапрос
     */
    @Test
    public void subSelectTest() throws Exception {
        // Заполняем таблицы Item, Bid
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            Item item1 = new Item("laptop");
            new Bid(item1, new BigDecimal("25000"));
            new Bid(item1, new BigDecimal("26500"));
            new Bid(item1, new BigDecimal("27000"));
            em.persist(item1);
            LOG.info("item1: {}", item1);

            Item item2 = new Item("bike 'Format 1316'");
            new Bid(item2, new BigDecimal("18000"));
            new Bid(item2, new BigDecimal("20000"));
            em.persist(item2);
            LOG.info("item2: {}", item2);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }

        // Добавляем ещё запись Bid, и извлекаем ItemBidSummary
        em = EMF.createEntityManager();
        try {
            TX.begin();

            Item item = em.createQuery("select i from Item i where i.id = 1",Item.class).getSingleResult();
            new Bid(item, new BigDecimal("28500"));
            em.persist(item);
            LOG.info("item: {}", item);

            // Перед выполнением запроса, Hibernate должен синхронизировать Item и Bid с БД
            ItemBidSummary itemBidSummary = em
                    .createQuery("select ibs from ItemBidSummary ibs where ibs.itemId = 1", ItemBidSummary.class)
                    .getSingleResult();
            LOG.info("itemBidSummary: {}", itemBidSummary);
            assertEquals(1L, itemBidSummary.getItemId().longValue());
            assertEquals("laptop", itemBidSummary.getName());
            assertEquals(4L, itemBidSummary.getNumberOfBids());

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }
}
