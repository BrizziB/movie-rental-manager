package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Order;

import javax.transaction.Transactional;

public class OrderDao extends BaseDao<Order> {

    public OrderDao() {
        super(Order.class);
    }

    @Transactional
    public void update(Order o) {
        Order oldOrder = getEm().find(Order.class, o.getId());
        oldOrder.setCustomer(o.getCustomer());
        oldOrder.setItems(o.getItems());
        oldOrder.setPaymentProfile(o.getPaymentProfile());
        oldOrder.setPayment(o.getPayment());
        oldOrder.setOrderStatus(o.getOrderStatus());
        oldOrder.setRentalType(o.getRentalType());
        oldOrder.setTotal(o.getTotal());
        oldOrder.setDelivery(o.getDelivery());
    }

}
