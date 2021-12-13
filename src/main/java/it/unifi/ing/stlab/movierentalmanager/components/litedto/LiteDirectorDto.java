package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LiteDirectorDto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LiteDirectorDto)) return false;
        LiteDirectorDto that = (LiteDirectorDto) o;
        return Objects.equals(name, that.name) && Objects.equals(birthDate, that.birthDate) && Objects.equals(country, that.country);
    }

}
