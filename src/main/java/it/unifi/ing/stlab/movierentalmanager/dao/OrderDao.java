package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.purchases.Order;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Stateless
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
        oldOrder.setOrderStatus(o.getOrderStatus());
        oldOrder.setRentalType(o.getRentalType());
        oldOrder.setDelivery(o.getDelivery());
        oldOrder.computeDiscountedTotal();
    }

    public List<Order> retrieveOrdersByIDs(List<Long> IDs) {
        TypedQuery<Order> query = getEm().createQuery(
                "FROM Order o WHERE o.id IN :IDs",
                Order.class
        ).setParameter("IDs", IDs);
        return query.getResultList();
    }

    public List<Order> retrieveOrdersBetweenDates(Date start, Date end) {
        TypedQuery<Order> query = getEm().createQuery(
                "FROM Order o WHERE o.lastUpdateTime > :start AND o.lastUpdateTime < :end",
                Order.class
        ).setParameter("start", start)
         .setParameter("end", end);
        return query.getResultList();
    }

}
