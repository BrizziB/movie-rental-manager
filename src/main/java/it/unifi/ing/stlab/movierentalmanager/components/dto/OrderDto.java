package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.purchases.Delivery;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.OrderStatus;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.RentalType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderDto implements Serializable {

    private CustomerDto customer;
    private PaymentProfileDto paymentProfile;
    private OrderStatus orderStatus;
    private RentalType rentalType;
    private BigDecimal total;
    private Delivery delivery;
    private List<DigitalMovieItemDto> digitalItems;
    private List<PhysicalMovieItemDto> physicalItems;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public PaymentProfileDto getPaymentProfile() {
        return paymentProfile;
    }

    public void setPaymentProfile(PaymentProfileDto paymentProfile) {
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

    public List<DigitalMovieItemDto> getDigitalItems() {
        return digitalItems;
    }

    public void setDigitalItems(List<DigitalMovieItemDto> digitalItems) {
        this.digitalItems = digitalItems;
    }

    public List<PhysicalMovieItemDto> getPhysicalItems() {
        return physicalItems;
    }

    public void setPhysicalItems(List<PhysicalMovieItemDto> physicalItems) {
        this.physicalItems = physicalItems;
    }

}
