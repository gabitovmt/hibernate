package com.example.helloworld;

import com.example.env.TransactionManagerTest;
import com.example.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.resource.transaction.backend.jta.internal.JtaTransactionCoordinatorBuilderImpl;
import org.hibernate.service.ServiceRegistry;
import org.testng.annotations.Test;

import javax.transaction.UserTransaction;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class HelloWorldHibernate extends TransactionManagerTest {

    private SessionFactory createSessionFactoryWithConfigXml() {
        return new MetadataSources(
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build()
        ).buildMetadata().buildSessionFactory();
    }

    private SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder
                .applySetting(Environment.DATASOURCE, "myDS")
                .applySetting(Environment.DIALECT, H2Dialect.class)
                .applySetting(Environment.TRANSACTION_COORDINATOR_STRATEGY, JtaTransactionCoordinatorBuilderImpl.class)
                .applySetting(Environment.HBM2DDL_AUTO, "create-drop")
                .applySetting(Environment.FORMAT_SQL, "true")
                .applySetting(Environment.USE_SQL_COMMENTS, "true");
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Message.class);
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.build();

        assertEquals(metadata.getEntityBindings().size(), 1);

        return metadata.buildSessionFactory();
    }

    @Test
    public void storeLoadMessageWithConfigXml() throws Exception {
        try (SessionFactory sessionFactory = createSessionFactoryWithConfigXml()) {
            storeMessage(sessionFactory);
            loadMessage(sessionFactory);
        } finally {
            TM.rollback();
        }
    }


    @Test
    public void storeLoadMessage() throws Exception {
        try (SessionFactory sessionFactory = createSessionFactory()) {
            storeMessage(sessionFactory);
            loadMessage(sessionFactory);
        } finally {
            TM.rollback();
        }
    }

    private void storeMessage(SessionFactory sessionFactory) throws Exception {
        LOG.info("storeMessage() is started");

        UserTransaction tx = TM.getUserTransaction();
        tx.begin();

        Session session = sessionFactory.getCurrentSession();

        Message message = new Message();
        message.setText("Hello World!");

        session.persist(message);

        tx.commit();

        LOG.info("storeMessage() is finished");
    }

    private void loadMessage(SessionFactory sessionFactory) throws Exception {
        LOG.info("loadMessage() is started");

        UserTransaction tx = TM.getUserTransaction();
        tx.begin();

        Session session = sessionFactory.getCurrentSession();

        List<Message> messages = session.createCriteria(Message.class).list();

        assertEquals(messages.size(), 1);
        assertEquals(messages.get(0).getText(), "Hello World!");

        messages.get(0).setText("Take me to your leader!");

        tx.commit();

        LOG.info("loadMessage() is finished");
    }
}
