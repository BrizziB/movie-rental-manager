package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.users.CustomerDetails;
import it.unifi.ing.stlab.movierentalmanager.model.users.Membership;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import java.io.Serializable;
import java.util.List;

public class LiteCustomerDto implements Serializable {

    private String name;
    private String address;
    private String phoneNumber;
    private WebUser webUser;
    private Membership membership;
    private CustomerDetails customerDetails;
    private List<LitePaymentProfileDto> paymentProfiles;
//    private List<LiteOrderDto> orders;

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

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public List<LitePaymentProfileDto> getPaymentProfiles() {
        return paymentProfiles;
    }

    public void setPaymentProfiles(List<LitePaymentProfileDto> paymentProfiles) {
        this.paymentProfiles = paymentProfiles;
    }
}
