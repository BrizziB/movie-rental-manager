package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payment_profiles")
public class PaymentProfile extends BaseEntity {

    @ManyToOne private Customer customer;
    private String creditCartType;
    private String creditCartNumber;

    public PaymentProfile() {
        super();
    }

    public PaymentProfile(UUID uuid) {
        super(uuid);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCreditCartType() {
        return creditCartType;
    }

    public void setCreditCartType(String creditCartType) {
        this.creditCartType = creditCartType;
    }

    public String getCreditCartNumber() {
        return creditCartNumber;
    }

    public void setCreditCartNumber(String creditCartNumber) {
        this.creditCartNumber = creditCartNumber;
    }

}
