package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;

public class PaymentProfileDto implements Serializable {

    private Long customerID;
    private String creditCardType;
    private String creditCardNumber;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
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
