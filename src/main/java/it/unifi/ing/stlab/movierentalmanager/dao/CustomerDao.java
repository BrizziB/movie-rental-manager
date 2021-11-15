package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.purchases.Order;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;
import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Customer findByUsername(String username) {
        TypedQuery<Customer> query = getEm().createQuery(
                "SELECT c FROM Customer c WHERE c.webUser.username = :username",
                Customer.class
        ).setParameter("username", username);
        return query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Customer> retrieveCustomersByName(String name) {
        TypedQuery<Customer> query = getEm().createQuery(
                "FROM Customer c WHERE c.name LIKE CONCAT('%', :name, '%')",
                Customer.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    public Optional<Long> retrieveCustomerIDByUsername(String username) {
        TypedQuery<Long> query = getEm().createQuery(
                "SELECT c.id FROM Customer c WHERE c.webUser.username = :username",
                Long.class
        ).setParameter("username", username);
        return Optional.ofNullable( query.getSingleResult() );
    }

    public WebUser retrieveWebUserByUsername(String username) {
        TypedQuery<WebUser> query = getEm().createQuery(
                "SELECT c.webUser FROM Customer c WHERE c.webUser.username = :username",
                WebUser.class
        ).setParameter("username", username);
        return query.getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
    }

    public List<Long> retrieveOrdersIDs(Long id) {
        Customer c = findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return c.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList());
    }

    @Transactional
    public Customer fetchCustomerWithPaymentProfiles(Long id) {
        TypedQuery<Customer> query = getEm().createQuery(
                "FROM Customer c JOIN FETCH c.paymentProfiles WHERE c.id = :id",
                Customer.class
        ).setParameter("id", id);
        return query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Customer fetchCustomerWithOrders(Long id) {
        TypedQuery<Customer> query = getEm().createQuery(
                "FROM Customer c JOIN FETCH c.orders WHERE c.id = :id",
                Customer.class
        ).setParameter("id", id);
        return query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

}
