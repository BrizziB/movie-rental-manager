package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "people")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "person_role")
public abstract class Person extends BaseEntity {

    private String name;
    private String surname;

    @Temporal(TemporalType.DATE) private Date birthDate;
    private String country;
    @Lob private String biography;

    public Person(){

    }

    public Person(UUID uuid){
        super(uuid);
    }

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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

}
