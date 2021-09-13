package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;

public class PaymentProfileDto implements Serializable {

    private CustomerDto customer;
    private String creditCardType;
    private String creditCardNumber;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

}
