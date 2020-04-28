package com.example.helloworld;

import com.example.env.TransactionManagerTest;
import com.example.model.Message;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HelloWorldJPA extends TransactionManagerTest {

    @Test
    public void storeLoadMessage() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
        try {
            storeMessage(emf);
            loadMessage(emf);
        } finally {
            TM.rollback();
            emf.close();
        }
    }

    private void storeMessage(EntityManagerFactory emf) throws Exception {
        LOG.info("storeMessage() is started");

        UserTransaction tx = TM.getUserTransaction();
        tx.begin();

        EntityManager em = emf.createEntityManager();

        Message message = new Message();
        message.setText("Hello World!");

        em.persist(message);

        tx.commit();

        em.close();

        LOG.info("storeMessage() is finished");
    }

    private void loadMessage(EntityManagerFactory emf) throws Exception {
        LOG.info("loadMessage() is started");

        UserTransaction tx = TM.getUserTransaction();
        tx.begin();

        EntityManager em = emf.createEntityManager();

        List<Message> messages = em.createQuery("select m from Message m").getResultList();

        assertEquals(1, messages.size());
        assertEquals("Hello World!", messages.get(0).getText());

        messages.get(0).setText("Take me to your leader!");

        tx.commit();

        em.close();

        LOG.info("loadMessage() is finished");
    }
}
