package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Embeddable
public class CustomerDetails {

    @OneToMany
    private List<Movie> favMovies;
    @OneToMany
    private List<Person> favActors;
    @OneToMany
    private List<Person> favDirectors;
//    @OneToMany
//    private List<Genres> favGenres;

    public CustomerDetails() {
        super();
    }

    public List<Movie> getFavMovies() {
        return favMovies;
    }

    public void setFavMovies(List<Movie> favMovies) {
        this.favMovies = favMovies;
    }

    public List<Person> getFavActors() {
        return favActors;
    }

    public void setFavActors(List<Person> favActors) {
        this.favActors = favActors;
    }

    public List<Person> getFavDirectors() {
        return favDirectors;
    }

    public void setFavDirectors(List<Person> favDirectors) {
        this.favDirectors = favDirectors;
    }

    /*public List<Genres> getFavGenres() {
        return favGenres;
    }

    public void setFavGenres(List<Genres> favGenres) {
        this.favGenres = favGenres;
    }*/

}
