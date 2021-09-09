package it.unifi.ing.stlab.movierentalmanager.model.movies;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "directors")
@DiscriminatorValue("director")
public class Director extends Person {

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;

    public Director() {
        super();
        movies = new ArrayList<Movie>();
    }

    public Director(UUID uuid) {
        super(uuid);
        movies = new ArrayList<Movie>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
