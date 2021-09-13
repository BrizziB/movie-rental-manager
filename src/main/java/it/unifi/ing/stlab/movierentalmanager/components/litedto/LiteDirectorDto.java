package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LiteDirectorDto implements Serializable {

    private String name;
    private Date birthDate;
    private String country;
//    private String biography;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
