package com.example.model.generatedvalue;

import com.example.env.SimpleTransactionManagerTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;

public class GeneratedValueTest extends SimpleTransactionManagerTest {

    @BeforeClass
    public static void init() {
        initEntityManagerFactory("map");
    }

    /**
     * Пример использования @GeneratedValue(strategy = GenerationType.AUTO)
     */
    @Test
    public void autoTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            Auto item = new Auto("value");
            em.persist(item);

            LOG.info("Auto: {}", item);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @GeneratedValue(strategy = GenerationType.SEQUENCE)
     */
    @Test
    public void sequenceTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            Sequence item = new Sequence("value");
            em.persist(item);

            LOG.info("Sequence: {}", item);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @GeneratedValue(strategy = GenerationType.IDENTITY)
     */
    @Test
    public void identityTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            Identity item = new Identity("value");
            em.persist(item);

            LOG.info("Identity: {}", item);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @GeneratedValue(strategy = GenerationType.TABLE)
     */
    @Test
    public void tableTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            Table item = new Table("value");
            em.persist(item);

            LOG.info("Table: {}", item);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @GeneratedValue(strategy = GenerationType.TABLE).
     * Сохраняется несколько объектов, чтобы посмотреть, как генерируются SQL-запросы
     */
    @Test
    public void tableTests() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            Table item = new Table("value1");
            em.persist(item);
            LOG.info("Table: {}", item);
            item = new Table("value2");
            em.persist(item);
            LOG.info("Table: {}", item);
            item = new Table("value3");
            em.persist(item);
            LOG.info("Table: {}", item);
            item = new Table("value4");
            em.persist(item);
            LOG.info("Table: {}", item);
            item = new Table("value5");
            em.persist(item);
            LOG.info("Table: {}", item);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @SequenceGenerator(name = "SEQUENCE_GENERATOR", sequenceName = "custom_seq_generator")
     */
    @Test
    public void nameSequenceTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            NameSequence item1 = new NameSequence("value1");
            NameSequence item2 = new NameSequence("value2");
            NameSequence item3 = new NameSequence("value3");
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            LOG.info("NameSequences: {}, {}, {}", item1, item2, item3);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @TableGenerator(name = "TABLE_GENERATOR", table = "custom_table_generator")
     */
    @Test
    public void nameTableTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            NameTable item1 = new NameTable("value1");
            NameTable item2 = new NameTable("value2");
            NameTable item3 = new NameTable("value3");
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            LOG.info("NameTable: {}, {}, {}", item1, item2, item3);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @org.hibernate.annotations.GenericGenerator
     */
    @Test
    public void idGeneratorTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            IdGenerator item1 = new IdGenerator("value1");
            IdGenerator item2 = new IdGenerator("value2");
            IdGenerator item3 = new IdGenerator("value3");
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            LOG.info("IdGenerator: {}, {}, {}", item1, item2, item3);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @org.hibernate.annotations.GenericGenerator. Strategy - guid
     */
    @Test
    @Ignore // H2 не поддерживает стратегию guid
    public void guidTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            GUID item1 = new GUID("value1");
            GUID item2 = new GUID("value2");
            GUID item3 = new GUID("value3");
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            LOG.info("GUID: {}, {}, {}", item1, item2, item3);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }

    /**
     * Пример использования @org.hibernate.annotations.GenericGenerator. Strategy - uuid
     */
    @Test
    public void uuidTest() throws Exception {
        EntityManager em = EMF.createEntityManager();
        try {
            TX.begin();

            UUID item1 = new UUID("value1");
            UUIDHex item2 = new UUIDHex("value2");
            UUIDString item3 = new UUIDString("value3");
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            LOG.info("UUID: {}, {}, {}", item1, item2, item3);

            TX.commit();
        } finally {
            TM.rollback();
            em.close();
        }
    }
}
