package com.example.model.simple;

import com.example.env.TransactionManagerTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

public class SimpleXml extends TransactionManagerTest {

    private EntityManagerFactory simpleXmlCompleteEmf;
    private EntityManagerFactory simpleXmlEmf;
    private UserTransaction tx;

    @BeforeSuite()
    public void init() {
        simpleXmlCompleteEmf = Persistence.createEntityManagerFactory("SimpleXMLCompletePU");
        simpleXmlEmf = Persistence.createEntityManagerFactory("SimpleXMLPU");
        tx = TM.getUserTransaction();
    }

    @AfterSuite(alwaysRun = true)
    public void stop() {
        if (simpleXmlCompleteEmf != null) {
            simpleXmlCompleteEmf.close();
        }
        if (simpleXmlEmf != null) {
            simpleXmlEmf.close();
        }
    }

    @Test
    public void simpleXmlComplete() throws Exception {
        try {
            tx.begin();
            EntityManager em = simpleXmlCompleteEmf.createEntityManager();

            Item item = new Item();
            item.setName("Name");

            em.persist(item);

            tx.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }

    @Test
    public void simpleXml() throws Exception {
        try {
            tx.begin();
            EntityManager em = simpleXmlEmf.createEntityManager();

            Item item = new Item();
            item.setName("Name");

            em.persist(item);

            tx.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }
}
