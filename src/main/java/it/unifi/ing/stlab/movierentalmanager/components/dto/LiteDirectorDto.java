package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.CrewMember;
import it.unifi.ing.stlab.movierentalmanager.model.CrewRole;
import it.unifi.ing.stlab.movierentalmanager.model.Director;

import java.io.Serializable;
import java.util.Date;

public class LiteDirectorDto implements Serializable {

    private String name;
    private String surname;
    private Date birthDate;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
