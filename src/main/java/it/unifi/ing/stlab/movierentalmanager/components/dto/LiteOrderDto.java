package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LiteOrderDto implements Serializable {

    private LiteCustomerDto customer;
    // TODO riunire le due liste in una sola, se faccio gerarchia di DTOs
    private List<LitePhysicalMovieItemDto> physicalItems;
    private List<LiteDigitalMovieItemDto> digitalItems;
    private LitePaymentProfileDto paymentProfile;
    private Payment payment;
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

    public List<LitePhysicalMovieItemDto> getPhysicalItems() {
        return physicalItems;
    }

    public void setPhysicalItems(List<LitePhysicalMovieItemDto> physicalItems) {
        this.physicalItems = physicalItems;
    }

    public List<LiteDigitalMovieItemDto> getDigitalItems() {
        return digitalItems;
    }

    public void setDigitalItems(List<LiteDigitalMovieItemDto> digitalItems) {
        this.digitalItems = digitalItems;
    }

    public LitePaymentProfileDto getPaymentProfile() {
        return paymentProfile;
    }

    public void setPaymentProfile(LitePaymentProfileDto paymentProfile) {
        this.paymentProfile = paymentProfile;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
