package it.unifi.ing.stlab.movierentalmanager.model.users;

import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    private String name;
    private String address;
    private String phoneNumber;
    @Embedded private WebUser webUser;
    @Enumerated(EnumType.STRING) private OfficeRole role;

    public Employee() {
        super();
    }

    public Employee(UUID uuid) {
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

    public OfficeRole getRole() {
        return role;
    }

    public void setRole(OfficeRole role) {
        this.role = role;
    }

}
