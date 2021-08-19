package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.PaymentProfile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentProfileDao {

    @PersistenceContext
    private EntityManager em;

    public void addPaymentProfile(PaymentProfile pp) {
        em.persist(pp);
    }

    public PaymentProfile findPaymentProfileById(Long id) {
        return em.find(PaymentProfile.class, id);
    }

    public List<PaymentProfile> findAllPaymentProfiles() {
        TypedQuery<PaymentProfile> query = em.createQuery("FROM PaymentProfile", PaymentProfile.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    public void updatePaymentProfile(PaymentProfile pp) {
        PaymentProfile oldPaymentProfile = em.find(PaymentProfile.class, pp.getId());
        oldPaymentProfile.setCustomer(pp.getCustomer());
        oldPaymentProfile.setCreditCartType(pp.getCreditCartType());
        oldPaymentProfile.setCreditCartNumber(pp.getCreditCartNumber());
    }

    public void savePaymentProfile(PaymentProfile pp) {
        if(pp.getId() != null)
            em.merge(pp);
        else
            em.persist(pp);
    }

    public int deletePaymentProfileById(Long id) {
        Query query = em.createQuery("DELETE FROM PaymentProfile pp WHERE pp.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
