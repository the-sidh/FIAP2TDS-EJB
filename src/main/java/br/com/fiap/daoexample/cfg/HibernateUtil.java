package br.com.fiap.daoexample.cfg;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class HibernateUtil {
    public static EntityManager getEntityManager() {
        EntityManager em =Persistence.createEntityManagerFactory("smartcities-orm").createEntityManager();
        return em;
    }
}