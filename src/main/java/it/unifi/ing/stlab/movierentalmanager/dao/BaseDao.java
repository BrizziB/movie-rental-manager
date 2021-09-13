package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends BaseEntity> {

    @PersistenceContext private EntityManager em;
    private Class<T> entityType;

    @SuppressWarnings("unchecked")
    public BaseDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    public EntityManager getEm() {
        return em;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    @Transactional
    public void add(T t) {
        em.persist(t);
    }

    @Transactional
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(em.find(entityType, id));
    }

    public List<T> findAll(Integer offset, Integer limit) {
        TypedQuery<T> query = em.createQuery("SELECT FROM " + entityType.getName(), entityType)
                                        .setFirstResult(offset)
                                        .setMaxResults(limit);
        return query.getResultList();
    }

    // UPDATE method cannot be implemented here

    @Transactional
    public void save(T t) {
        if(t.getId() != null)
            em.merge(t);
        else
            em.persist(t);
    }

    @Transactional
    public void deleteById(Long id) {
        em.remove( em.contains( em.find(entityType, id) ) );
    }

}
