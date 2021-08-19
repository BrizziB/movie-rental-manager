package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addCustomer(Customer c) {
        em.persist(c);
    }

    public Optional<Customer> findCustomerById(Long id) {
        return Optional.ofNullable(em.find(Customer.class, id));
    }

    public List<Customer> findAllCustomers() {
        TypedQuery<Customer> query = em.createQuery("FROM Customer", Customer.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    @Transactional
    public void updateCustomer(Customer c) {
        Customer oldCustomer = em.find(Customer.class, c.getId());
        oldCustomer.setName(c.getName());
        oldCustomer.setAddress(c.getAddress());
        oldCustomer.setPhoneNumber(c.getPhoneNumber());
        oldCustomer.setWebUser(c.getWebUser());
        oldCustomer.setCustomerDetails(c.getCustomerDetails());
        oldCustomer.setPaymentProfiles(c.getPaymentProfiles());
        oldCustomer.setOrders(c.getOrders());
        oldCustomer.setMembership(c.getMembership());
    }

    @Transactional
    public void saveCustomer(Customer c) {
        if(c.getId() != null)
            em.merge(c);
        else
            em.persist(c);
    }

    @Transactional
    public int deleteCustomerById(Long id) {
        Query query = em.createQuery("DELETE FROM Customer c WHERE c.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
