package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne private Customer customer;

    @ManyToMany
    @JoinTable(name = "orders_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<MovieItem> items;

    @ManyToOne private PaymentProfile paymentProfile;
    @Embedded private Payment payment;
    @Enumerated(EnumType.STRING) private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING) private RentalType rentalType;
    private BigDecimal total;
    @Embedded private Delivery delivery;

    public Order() {
        super();
        items = new ArrayList<MovieItem>();
    }

    public Order(UUID uuid) {
        super(uuid);
        items = new ArrayList<MovieItem>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<MovieItem> getItems() {
        return items;
    }

    public void setItems(List<MovieItem> items) {
        this.items = items;
    }

    public PaymentProfile getPaymentProfile() {
        return paymentProfile;
    }

    public void setPaymentProfile(PaymentProfile paymentProfile) {
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
