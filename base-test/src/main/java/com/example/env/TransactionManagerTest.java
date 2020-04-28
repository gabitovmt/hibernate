package com.example.env;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.UserTransaction;
import java.util.Locale;

public class TransactionManagerTest {
    public static final Logger LOG = LoggerFactory.getLogger(TransactionManagerSetup.class);
    public static TransactionManagerSetup TM;
    public static UserTransaction TX;

    private static void initTM(String database, String connectionURL) throws Exception {
        DatabaseProduct dbProduct = database != null
                ? DatabaseProduct.valueOf(database.toUpperCase(Locale.US))
                : DatabaseProduct.H2;
        TM = new TransactionManagerSetup(dbProduct, connectionURL);
        LOG.info("TransactionManagerSetup is initialized");
    }

    @BeforeClass
    public static void beforeAll() throws Exception {
        initTM(null, null);
        TX = TM.getUserTransaction();
    }

    @AfterClass
    public static void afterAll() {
        if (TM != null) {
            TM.stop();
            TX = null;
        }
    }
}