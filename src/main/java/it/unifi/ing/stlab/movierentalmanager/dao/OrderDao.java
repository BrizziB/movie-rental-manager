package it.unifi.ing.stlab.movierentalmanager.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import it.unifi.ing.stlab.movierentalmanager.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addOrder(Order o) {
        em.persist(o);
    }

    public Optional<Order> findOrderById(Long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    public List<Order> findAllOrders() {
        TypedQuery<Order> query = em.createQuery("FROM Order", Order.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    @Transactional
    public void updateOrder(Order o) {
        Order oldOrder = em.find(Order.class, o.getId());
        oldOrder.setCustomer(o.getCustomer());
        oldOrder.setItems(o.getItems());
        oldOrder.setPaymentProfile(o.getPaymentProfile());
        oldOrder.setPayment(o.getPayment());
        oldOrder.setOrderStatus(o.getOrderStatus());
        oldOrder.setRentalType(o.getRentalType());
        oldOrder.setTotal(o.getTotal());
        oldOrder.setDelivery(o.getDelivery());
    }

    @Transactional
    public void saveOrder(Order o) {
        if(o.getId() != null)
            em.merge(o);
        else
            em.persist(o);
    }

    @Transactional
    public int deleteOrderById(Long id) {
        Query query = em.createQuery("DELETE FROM Order o WHERE o.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
