package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    private String name;
    private String address;
    private String phoneNumber;
    @Embedded
    private WebUser webUser;
    @Enumerated(EnumType.STRING)
    private Membership membership;
    @Embedded
    private CustomerDetails customerDetails;

    public Customer() {
        super();
    }

    public Customer(UUID uuid) {
        super(uuid);
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
}
