package com.example.model.simple;

import com.example.env.TransactionManagerTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleXml extends TransactionManagerTest {

    @Test
    public void simpleXmlCompleteJpa() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleXMLCompletePU");
        try {
            TX.begin();
            EntityManager em = emf.createEntityManager();

            Item item = new Item();
            item.setName("Name");

            em.persist(item);

            TX.commit();
            em.close();
        } finally {
            TM.rollback();
            emf.close();
        }
    }

    @Test
    public void simpleXmlJpa() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleXMLPU");
        try {
            TX.begin();
            EntityManager em = emf.createEntityManager();

            Item item = new Item();
            item.setName("Name");

            em.persist(item);

            TX.commit();
            em.close();
        } finally {
            TM.rollback();
            emf.close();
        }
    }

    @Test
    public void simpleXmlHibernate() throws Exception {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("simple_xml.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        try (SessionFactory sf = metadata.buildSessionFactory()) {
            TX.begin();
            Session session = sf.getCurrentSession();

            Item item = new Item();
            item.setName("Name");

            session.persist(item);

            TX.commit();
        } finally {
            TM.rollback();
        }
    }
}
