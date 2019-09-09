package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityStuff {
    EntityManagerFactory emf;

    public void saveToDb(Url url){
        emf = Persistence.createEntityManagerFactory("com.example.demo");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(url);
        em.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
