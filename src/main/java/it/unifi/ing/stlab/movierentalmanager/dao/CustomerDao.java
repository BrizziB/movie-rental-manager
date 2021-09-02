package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class CustomerDao extends BaseDao<Customer> {

    public CustomerDao() {
        super(Customer.class);
    }

    @Transactional
    public void update(Customer c) {
        Customer oldCustomer = getEm().find(Customer.class, c.getId());
        oldCustomer.setName(c.getName());
        oldCustomer.setAddress(c.getAddress());
        oldCustomer.setPhoneNumber(c.getPhoneNumber());
        oldCustomer.setWebUser(c.getWebUser());
        oldCustomer.setCustomerDetails(c.getCustomerDetails());
        oldCustomer.setPaymentProfiles(c.getPaymentProfiles());
        oldCustomer.setOrders(c.getOrders());
        oldCustomer.setMembership(c.getMembership());
    }

    public List<Customer> retrieveCustomersByName(String name) {
        TypedQuery<Customer> query = getEm().createQuery(
                "FROM Customer c WHERE c.name LIKE CONCAT('%', :name, '%')",
                Customer.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    @Transactional
    public Customer fetchCustomerWithPaymentProfiles(Long id) {
        TypedQuery<Customer> query = getEm().createQuery(
                "FROM Customer c JOIN FETCH c.paymentProfiles WHERE c.id = :id",
                Customer.class
        ).setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public Customer fetchCustomerWithOrders(Long id) {
        TypedQuery<Customer> query = getEm().createQuery(
                "FROM Customer c JOIN FETCH c.orders WHERE c.id = :id",
                Customer.class
        ).setParameter("id", id);
        return query.getSingleResult();
    }

}
