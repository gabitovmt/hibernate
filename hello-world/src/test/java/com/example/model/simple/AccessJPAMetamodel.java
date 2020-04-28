package com.example.model.simple;

import com.example.env.TransactionManagerTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccessJPAMetamodel extends TransactionManagerTest {

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void init() throws Exception {
        emf = Persistence.createEntityManagerFactory("validationNonePU");

        try {
            TX.begin();
            EntityManager em = emf.createEntityManager();

            Item item1 = new Item();
            item1.setName("Marat");
            em.persist(item1);

            Item item2 = new Item();
            item2.setName("Axwer");
            em.persist(item2);

            em.close();
            TX.commit();
        } finally {
            TM.rollback();
        }
    }

    @AfterClass
    public static void stop() {
        if (emf != null) {
            emf.close();
        }
    }

    /**
     * Динамический интерфейс Metamodel API в Java Persistence
     */
    @Test
    public void dynamicAPI() {
        // Первый способ
        Metamodel mm = emf.getMetamodel();
        // Второй способ
        // Metamodel mm = emf.createEntityManager().getMetamodel();

        Set<ManagedType<?>> managedTypes = mm.getManagedTypes();
        assertEquals(1, managedTypes.size());

        ManagedType<?> itemType = managedTypes.iterator().next();
        assertEquals(Type.PersistenceType.ENTITY, itemType.getPersistenceType());

        // Атрибут сущности
        SingularAttribute<?,?> nameAttribute = itemType.getSingularAttribute("name");
        assertEquals(String.class, nameAttribute.getJavaType());
        assertEquals(Attribute.PersistentAttributeType.BASIC, nameAttribute.getPersistentAttributeType());
        assertTrue(nameAttribute.isOptional());

        // Атрибут сущности
        SingularAttribute<?,?> auctionEndAttribute = itemType.getSingularAttribute("auctionEnd");
        assertEquals(Date.class, auctionEndAttribute.getJavaType());
        assertFalse(auctionEndAttribute.isCollection());
        assertFalse(auctionEndAttribute.isAssociation());
    }

    /**
     * Динамический интерфейс Metamodel API и Criteria Query
     */
    @Test
    public void dynamicAndCriteria() throws Exception {
        try {
            TX.begin();
            EntityManager em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Эквивалент select i from Item i
            CriteriaQuery<Item> query = cb.createQuery(Item.class);
            Root<Item> fromItem = query.from(Item.class);
            query.select(fromItem);

            List<Item> items = em.createQuery(query).getResultList();
            LOG.info("items: {}", items);
            assertEquals(2, items.size());

            // Эквивалент select i from Item i where i.name like '%Marat%'
            Path<String> namePath = fromItem.get("name");
            query.where(cb.like(namePath, cb.parameter(String.class, "pattern")));

            items = em.createQuery(query)
                    .setParameter("pattern", "%Marat%")
                    .getResultList();
            LOG.info("items: {}", items);
            assertEquals(1, items.size());
            assertEquals("Marat", items.iterator().next().getName());

            em.close();
            TX.commit();
        } finally {
            TM.rollback();
        }
    }

    /**
     * Статический интерфейс Metamodel API в Java Persistence
     */
    @Test
    public void staticAPI() throws Exception {
        try {
            TX.begin();
            EntityManager em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Эквивалент select i from Item i where i.name like '%Marat%'
            CriteriaQuery<Item> query = cb.createQuery(Item.class);
            Root<Item> fromItem = query.from(Item.class);
            query.select(fromItem);
            Path<String> namePath = fromItem.get(Item_.name);
            query.where(cb.like(namePath, cb.parameter(String.class, "pattern")));

            List<Item> items = em.createQuery(query)
                    .setParameter("pattern", "%Marat%")
                    .getResultList();
            LOG.info("items: {}", items);
            assertEquals(1, items.size());
            assertEquals("Marat", items.iterator().next().getName());

            em.close();
            TX.commit();
        } finally {
            TM.rollback();
        }
    }
}
