package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import it.unifi.ing.stlab.movierentalmanager.model.users.CustomerDetails;
import it.unifi.ing.stlab.movierentalmanager.model.users.Membership;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import java.io.Serializable;
import java.util.List;

public class LiteCustomerDto {

    private String name;
    private String address;
    private String phoneNumber;
    private WebUser webUser;
    private Membership membership;
    private CustomerDetails customerDetails;


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

}
