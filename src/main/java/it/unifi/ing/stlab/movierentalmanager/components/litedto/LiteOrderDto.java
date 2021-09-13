package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import it.unifi.ing.stlab.movierentalmanager.model.purchases.Delivery;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.OrderStatus;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.RentalType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LiteOrderDto implements Serializable {

    private LiteCustomerDto customer;
    private LitePaymentProfileDto paymentProfile;
    private OrderStatus orderStatus;
    private RentalType rentalType;
    private BigDecimal total;
    private Delivery delivery;

    public LiteCustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(LiteCustomerDto customer) {
        this.customer = customer;
    }

    public LitePaymentProfileDto getPaymentProfile() {
        return paymentProfile;
    }

    public void setPaymentProfile(LitePaymentProfileDto paymentProfile) {
        this.paymentProfile = paymentProfile;
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

}
