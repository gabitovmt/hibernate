package com.example.model.simple;

import com.example.env.TransactionManagerTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolationException;
import java.util.Date;

public class ModelOperationSave extends TransactionManagerTest {

    private EntityManagerFactory validationAutoEmf;
    private EntityManagerFactory validationCallbackEmf;
    private EntityManagerFactory validationNoneEmf;
    private UserTransaction tx;

    @BeforeSuite()
    public void init() {
        validationAutoEmf = Persistence.createEntityManagerFactory("validationAutoPU");
        validationCallbackEmf = Persistence.createEntityManagerFactory("validationCallbackPU");
        validationNoneEmf = Persistence.createEntityManagerFactory("validationNonePU");
        tx = TM.getUserTransaction();
    }

    @AfterSuite(alwaysRun = true)
    public void stop() {
        if (validationAutoEmf != null) {
            validationAutoEmf.close();
        }
        if (validationCallbackEmf != null) {
            validationCallbackEmf.close();
        }
        if (validationNoneEmf != null) {
            validationNoneEmf.close();
        }
    }

    @Test
    public void validationAuto() throws Exception {
        try {
            tx.begin();
            EntityManager em = validationAutoEmf.createEntityManager();

            Item item = new Item();
            item.setName("Abc");
            item.setAuctionEnd(new Date());

            em.persist(item);

            tx.commit();
            em.close();
        } catch (Exception e) {
            Throwable cause = e.getCause().getCause();
            if (!(cause instanceof ConstraintViolationException)) {
                throw e;
            }
        } finally {
            TM.rollback();
        }
    }

    @Test
    public void validationCallback() throws Exception {
        try {
            tx.begin();
            EntityManager em = validationCallbackEmf.createEntityManager();

            Item item = new Item();
            item.setName("Abc");
            item.setAuctionEnd(new Date());

            em.persist(item);

            tx.commit();
            em.close();
        } catch (Exception e) {
            Throwable cause = e.getCause().getCause();
            if (!(cause instanceof ConstraintViolationException)) {
                throw e;
            }
        } finally {
            TM.rollback();
        }
    }

    @Test
    public void validationNone() throws Exception {
        try {
            tx.begin();
            EntityManager em = validationNoneEmf.createEntityManager();

            Item item = new Item();
            item.setName("Abc");
            item.setAuctionEnd(new Date());

            em.persist(item);

            tx.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }
}
