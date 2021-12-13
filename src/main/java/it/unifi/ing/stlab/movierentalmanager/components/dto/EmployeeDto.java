package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.users.OfficeRole;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import java.io.Serializable;

public class EmployeeDto {

    private String name;
    private String address;
    private String phoneNumber;
    private WebUser webUser;
    private OfficeRole role;

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

    public OfficeRole getRole() {
        return role;
    }

    public void setRole(OfficeRole role) {
        this.role = role;
    }

}
