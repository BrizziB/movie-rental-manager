package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue
    private String id;
    private String title;


}
