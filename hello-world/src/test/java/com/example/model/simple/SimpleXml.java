package com.example.model.simple;

import com.example.env.TransactionManagerTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleXml extends TransactionManagerTest {

    private static EntityManagerFactory simpleXmlCompleteEmf;
    private static EntityManagerFactory simpleXmlEmf;

    @BeforeClass()
    public static void init() {
        simpleXmlCompleteEmf = Persistence.createEntityManagerFactory("SimpleXMLCompletePU");
        simpleXmlEmf = Persistence.createEntityManagerFactory("SimpleXMLPU");
    }

    @AfterClass
    public static void stop() {
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
            TX.begin();
            EntityManager em = simpleXmlCompleteEmf.createEntityManager();

            Item item = new Item();
            item.setName("Name");

            em.persist(item);

            TX.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }

    @Test
    public void simpleXml() throws Exception {
        try {
            TX.begin();
            EntityManager em = simpleXmlEmf.createEntityManager();

            Item item = new Item();
            item.setName("Name");

            em.persist(item);

            TX.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }
}
