package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;

public class LitePaymentProfileDto implements Serializable {

    private LiteCustomerDto customer;
    private String creditCardType;
    private String creditCardNumber;

    public LiteCustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(LiteCustomerDto customer) {
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
