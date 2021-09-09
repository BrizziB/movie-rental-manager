package it.unifi.ing.stlab.movierentalmanager.model.movies;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "crew_members")
@DiscriminatorValue("crew_member")
public class CrewMember extends Person {

    @Enumerated(EnumType.STRING) private CrewRole role;

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

    public CrewRole getRole() {
        return role;
    }

    public void setRole(CrewRole role) {
        this.role = role;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
