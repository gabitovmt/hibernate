package com.example.model.immutable;

import com.example.env.SimpleTransactionManagerTest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

public class ImmutableEntityTest extends SimpleTransactionManagerTest {

    @BeforeClass
    public static void init() {
        initEntityManagerFactory("map");
    }

    /**
     * Неизменяемые сущности
     */
    @Test
    public void test() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            ImmutableItem item = new ImmutableItem("value");
            em.persist(item);
            LOG.info("ImmutableItem: {}", item);

            TX.commit();
            TX.begin();

            item.setValue("value10");
            em.persist(item);
            LOG.info("ImmutableItem: {}", item);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }

        // Нужно обязательно пересоздать EntityManager, т.к. ImmutableItem item хранится в кэше 1-го уровня.
        em = EMF.createEntityManager();
        try {
            TX.begin();

            ImmutableItem item = em.createQuery("select i from ImmutableItem i", ImmutableItem.class).getSingleResult();
            LOG.info("ImmutableItem: {}", item);
            assertEquals("value", item.getValue());

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }
}
