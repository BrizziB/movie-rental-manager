package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "payment_profiles")
public class PaymentProfile extends BaseEntity {

    @ManyToOne private Customer customer;
    private String creditCardType;
    private String creditCardNumber;

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

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCartType) {
        this.creditCardType = creditCartType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCartNumber) {
        this.creditCardNumber = creditCartNumber;
    }

}
