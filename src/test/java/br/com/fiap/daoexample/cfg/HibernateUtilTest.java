package br.com.fiap.daoexample.cfg;


import br.com.fiap.daoexample.domain.entities.Estabelecimento;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;

public class HibernateUtilTest {

    private static EntityManager em;

    @BeforeAll
    public static void setup() {
        em = HibernateUtil.getEntityManager();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (em != null) em.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    public void testCreate() {
        em.getTransaction().begin();
        Estabelecimento estabelecimento = new Estabelecimento();
        em.persist(estabelecimento);
        em.getTransaction().commit();
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testList() {
    }

    @Test
    public void testDelete() {
    }
}