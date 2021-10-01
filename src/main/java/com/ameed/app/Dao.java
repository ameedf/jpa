package com.ameed.app;

import com.ameed.entities.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class Dao {
    private final EntityManager em;

    public Dao() {
        em = Persistence.createEntityManagerFactory("MySQL_PU").createEntityManager();
    }

    public void save(AbstractEntity entity) {
        startTransaction();
        em.persist(entity);
        commitTransaction();
    }

    public void deleteAll(Class<? extends AbstractEntity> entityClass) {
        startTransaction();
        em.createQuery("DELETE FROM " + entityClass.getSimpleName());
        commitTransaction();
    }

    private void startTransaction() {
        if (em.getTransaction().isActive()) {
            return;
        }
        em.getTransaction().begin();
    }

    private void commitTransaction() {
        if (!em.getTransaction().isActive()) {
            startTransaction();
        }
        em.getTransaction().commit();
    }

    public <T> List<T> list(String query, Class<T> resultClass) {
        return em.createQuery(query, resultClass)
                .getResultList();
    }
}
