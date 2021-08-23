package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

}
