package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDao {

    @PersistenceContext
    private EntityManager em;

    public void addCustomer(Customer c) {
        em.persist(c);
    }

    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAllCustomers() {
        TypedQuery<Customer> query = em.createQuery("FROM Customer", Customer.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

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

    public void saveCustomer(Customer c) {
        if(c.getId() != null)
            em.merge(c);
        else
            em.persist(c);
    }

    public int deleteCustomerById(Long id) {
        Query query = em.createQuery("DELETE FROM Customer c WHERE c.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
