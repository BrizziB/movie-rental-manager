package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "crew_members")
@DiscriminatorValue("crew_member")
public class CrewMember extends Person {

    private String role;

    @ManyToMany(mappedBy = "crew")
    private List<Movie> movies;

    public CrewMember() {
        super();
        movies = new ArrayList<Movie>();
    }

    public CrewMember(UUID uuid) {
        super(uuid);
        movies = new ArrayList<Movie>();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
