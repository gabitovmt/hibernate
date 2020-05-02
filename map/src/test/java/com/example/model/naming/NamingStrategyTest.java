package com.example.model.naming;

import com.example.env.SimpleTransactionManagerTest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;

public class NamingStrategyTest extends SimpleTransactionManagerTest {

    @BeforeClass
    public static void init() {
        initEntityManagerFactory("naming-strategy");
    }

    /**
     * Проверка применения стратегий наименования.
     * Проверка именования сущностей для запросов.
     */
    @Test
    public void test() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            com.example.model.naming.SystemUser se1 =
                    new com.example.model.naming.SystemUser("marat@gmail.com");
            em.persist(se1);
            LOG.info("SystemUser: {}", se1);

            com.example.model.namingother.SystemUser se2 =
                    new com.example.model.namingother.SystemUser("rinat@gmail.com");
            em.persist(se2);
            LOG.info("SystemUser: {}", se2);

            TX.commit();

            TX.begin();

            se1 = em.createQuery(
                    "select su from SystemUser su", com.example.model.naming.SystemUser.class
            ).getSingleResult();
            LOG.info("SystemUser: {}", se1);

            se2 = em.createQuery(
                    "select su from OtherSystemUser su", com.example.model.namingother.SystemUser.class
            ).getSingleResult();
            LOG.info("SystemUser: {}", se2);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }
}
