package at.htl.business.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseFacade<T> {

    @PersistenceContext
    EntityManager em;

    public void save(T t) {
        em.persist(t);
    }

    public void delete(T t) {
        em.remove(t);
    }

    public T update(T t) {
        return em.merge(t);
    }

    public abstract List<T> getAll();

    public EntityManager getEntityManager() {
        return em;
    }
}
