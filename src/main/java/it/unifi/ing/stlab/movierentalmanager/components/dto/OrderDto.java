package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.purchases.Delivery;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.OrderStatus;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.RentalType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Long customerID;
    private Long paymentProfileID;
    private OrderStatus orderStatus;
    private RentalType rentalType;
    private BigDecimal total;
    private Delivery delivery;
    private List<Long> digitalItemsIDs;
    private List<Long> physicalItemsIDs;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getPaymentProfileID() {
        return paymentProfileID;
    }

    public void setPaymentProfileID(Long paymentProfileID) {
        this.paymentProfileID = paymentProfileID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<Long> getDigitalItemsIDs() {
        return digitalItemsIDs;
    }

    public void setDigitalItemsIDs(List<Long> digitalItemsIDs) {
        this.digitalItemsIDs = digitalItemsIDs;
    }

    public List<Long> getPhysicalItemsIDs() {
        return physicalItemsIDs;
    }

    public void setPhysicalItemsIDs(List<Long> physicalItemsIDs) {
        this.physicalItemsIDs = physicalItemsIDs;
    }

}
