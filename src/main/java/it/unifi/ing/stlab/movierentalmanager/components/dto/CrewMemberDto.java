package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.movies.CrewRole;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CrewMemberDto {

    private String name;
    private Date birthDate;
    private String country;
    private CrewRole role;
    private List<Long> moviesIDs;

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

    public CrewRole getRole() {
        return role;
    }

    public void setRole(CrewRole role) {
        this.role = role;
    }

    public List<Long> getMoviesIDs() {
        return moviesIDs;
    }

    public void setMoviesIDs(List<Long> moviesIDs) {
        this.moviesIDs = moviesIDs;
    }

}
