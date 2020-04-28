package com.example.model.simple;

import com.example.env.TransactionManagerTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;
import java.util.Date;

public class ModelOperationSave extends TransactionManagerTest {

    private static EntityManagerFactory validationAutoEmf;
    private static EntityManagerFactory validationCallbackEmf;
    private static EntityManagerFactory validationNoneEmf;

    @BeforeClass()
    public static void init() {
        validationAutoEmf = Persistence.createEntityManagerFactory("validationAutoPU");
        validationCallbackEmf = Persistence.createEntityManagerFactory("validationCallbackPU");
        validationNoneEmf = Persistence.createEntityManagerFactory("validationNonePU");
    }

    @AfterClass()
    public static void stop() {
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
            TX.begin();
            EntityManager em = validationAutoEmf.createEntityManager();

            Item item = new Item();
            item.setName("Abc");
            item.setAuctionEnd(new Date());

            em.persist(item);

            TX.commit();
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
            TX.begin();
            EntityManager em = validationCallbackEmf.createEntityManager();

            Item item = new Item();
            item.setName("Abc");
            item.setAuctionEnd(new Date());

            em.persist(item);

            TX.commit();
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
            TX.begin();
            EntityManager em = validationNoneEmf.createEntityManager();

            Item item = new Item();
            item.setName("Abc");
            item.setAuctionEnd(new Date());

            em.persist(item);

            TX.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }
}
