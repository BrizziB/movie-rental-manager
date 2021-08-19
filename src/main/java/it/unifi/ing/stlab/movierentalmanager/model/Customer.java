package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    private String name;
    private String address;
    private String phoneNumber;
    @Embedded private WebUser webUser;
    @Embedded private CustomerDetails customerDetails;
    @OneToMany(mappedBy = "customer") private List<PaymentProfile> paymentProfiles;
    @OneToMany(mappedBy = "customer") private List<Order> orders;
    @Enumerated(EnumType.STRING) private Membership membership;

    public Customer() {
        super();
        paymentProfiles = new ArrayList<PaymentProfile>();
    }

    public Customer(UUID uuid) {
        super(uuid);
        paymentProfiles = new ArrayList<PaymentProfile>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public List<PaymentProfile> getPaymentProfiles() {
        return paymentProfiles;
    }

    public void setPaymentProfiles(List<PaymentProfile> paymentProfiles) {
        this.paymentProfiles = paymentProfiles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

}
