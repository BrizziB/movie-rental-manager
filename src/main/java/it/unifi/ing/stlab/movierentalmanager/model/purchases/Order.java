package it.unifi.ing.stlab.movierentalmanager.model.purchases;

import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;
import it.unifi.ing.stlab.movierentalmanager.model.items.MovieItem;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne private Customer customer;
    @ManyToOne private PaymentProfile paymentProfile;
    @Enumerated private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING) private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING) private RentalType rentalType;
    @Transient private BigDecimal fullTotal = new BigDecimal(0.0);
    @Transient private BigDecimal discountedTotal = new BigDecimal(0.0);
    @Embedded private Delivery delivery;
    @Transient private List<Discounter> discounters;

    @ManyToMany
    @JoinTable(name = "orders_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<MovieItem> items;


    public Order() {
        super();
        items = new ArrayList<MovieItem>();
        discounters = new ArrayList<Discounter>();
    }

    public Order(UUID uuid) {
        super(uuid);
        items = new ArrayList<MovieItem>();
        discounters = new ArrayList<Discounter>();
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if(this.orderStatus == OrderStatus.CLOSED && orderStatus != OrderStatus.CLOSED)
            decrementPurchasesForEachItem();
        if(orderStatus == OrderStatus.CLOSED)
            incrementPurchasesForEachItem();
        this.orderStatus = orderStatus;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public BigDecimal getFullTotal() {
        if(fullTotal == BigDecimal.valueOf(0.0))
            computeFullTotal();
        return fullTotal;
    }

    public void computeFullTotal() {
        for(MovieItem mi : items)
            fullTotal = fullTotal.add(mi.getRentalPrice());
    }

    public BigDecimal getDiscountedTotal() {
        if(discountedTotal == BigDecimal.valueOf(0.0))
            computeDiscountedTotal();
        return discountedTotal;
    }

    public void computeDiscountedTotal() {
        computeFullTotal();
        discountedTotal = discounters.stream()
                                     .reduce(v -> v, Discounter::combine)
                                     .apply(fullTotal);
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<Discounter> getDiscounters() {
        return discounters;
    }

    public void setDiscounters(List<Discounter> discounters) {
        this.discounters = discounters;
    }

    private void incrementPurchasesForEachItem() {
        for(MovieItem mi : items) {
            mi.getMovie().incrementPurchases();
        }
    }

    private void decrementPurchasesForEachItem() {
        for(MovieItem mi : items) {
            mi.getMovie().decrementPurchases();
        }
    }

}