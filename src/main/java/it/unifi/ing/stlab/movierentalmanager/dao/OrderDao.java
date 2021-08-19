package it.unifi.ing.stlab.movierentalmanager.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.unifi.ing.stlab.movierentalmanager.model.Order;

import java.util.List;

public class OrderDao {

    @PersistenceContext
    private EntityManager em;

    public void addOrder(Order o) {
        em.persist(o);
    }

    public Order findOrderById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAllOrders() {
        TypedQuery<Order> query = em.createQuery("FROM Order", Order.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

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

    public void saveOrder(Order o) {
        if(o.getId() != null)
            em.merge(o);
        else
            em.persist(o);
    }

    public int deleteOrderById(Long id) {
        Query query = em.createQuery("DELETE FROM Order o WHERE o.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
