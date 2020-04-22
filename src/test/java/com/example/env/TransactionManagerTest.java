package com.example.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Locale;

/**
 * Starts and stops the transaction manager/database pool before/after a test suite.
 * <p>
 * All tests in a suite execute with a single {@link TransactionManagerSetup}, call
 * the static {@link TransactionManagerTest#TM} in your test to access the JTA
 * transaction manager and database connections.
 * </p>
 * <p>
 * The test parameters <code>database</code> (specifying a supported
 * {@link DatabaseProduct}) and a <code>connectionURL</code> are optional.
 * The default is an in-memory H2 database instance, created and destroyed
 * automatically for each test suite.
 * </p>
 */
public class TransactionManagerTest {

    public static final Logger LOG = LoggerFactory.getLogger(TransactionManagerSetup.class);

    // Static single database connection manager per test suite
    public static TransactionManagerSetup TM;

    @Parameters({"database", "connectionURL"})
    @BeforeSuite()
    public void beforeSuite(@Optional String database, @Optional String connectionURL) throws Exception {
        TM = new TransactionManagerSetup(
                database != null
                        ? DatabaseProduct.valueOf(database.toUpperCase(Locale.US))
                        : DatabaseProduct.H2,
                connectionURL
        );
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
        if (TM != null) {
            TM.stop();
        }
    }
}