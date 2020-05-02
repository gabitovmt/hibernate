package com.example.model.com.example.model.dynamic;

import com.example.env.SimpleTransactionManagerTest;
import com.example.model.dynamic.DynamicItem;
import com.example.model.dynamic.StaticItem;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;

public class DynamicSqlTest extends SimpleTransactionManagerTest {

    @BeforeClass
    public static void init() {
        initEntityManagerFactory("map");
    }

    /**
     * Динамическое формирование SQL
     */
    @Test
    public void dynamicSql() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            StaticItem staticItem = new StaticItem();
            staticItem.setValue1("value1");
            em.persist(staticItem);

            DynamicItem dynamicItem = new DynamicItem();
            dynamicItem.setValue1("value1");
            em.persist(dynamicItem);

            LOG.info("StaticItem: {}", staticItem);
            LOG.info("DynamicItem: {}", dynamicItem);

            TX.commit();

            TX.begin();

            staticItem.setValue2("value2");
            em.persist(staticItem);
            dynamicItem.setValue2("value2");
            em.persist(dynamicItem);

            LOG.info("StaticItem: {}", staticItem);
            LOG.info("DynamicItem: {}", dynamicItem);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }
}
