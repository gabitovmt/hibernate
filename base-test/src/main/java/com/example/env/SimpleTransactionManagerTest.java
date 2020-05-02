package com.example.env;

import org.junit.AfterClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleTransactionManagerTest extends TransactionManagerTest {
    public static EntityManagerFactory EMF;

    public static void initEntityManagerFactory(String persistenceUnitName) {
        EMF = Persistence.createEntityManagerFactory(persistenceUnitName);
        LOG.info("EntityManagerFactory is initialized");
    }
    @AfterClass
    public static void close() {
        if (EMF != null) {
            EMF.close();
        }
    }
}
