package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class CustomerDetails {

    @OneToMany @JoinTable(name = "customer_favactors")
    private List<Actor> favActors;
    @OneToMany @JoinTable(name = "customer_favdirectors")
    private List<CrewMember> favDirectors;
    @OneToMany @JoinTable(name = "customer_favmovies")
    private List<Movie> favMovies;
    @ElementCollection private List<Genre> favGenres;

    public CustomerDetails() {
        super();
        favActors = new ArrayList<Actor>();
        favDirectors = new ArrayList<CrewMember>();
        favMovies = new ArrayList<Movie>();
        favGenres = new ArrayList<Genre>();
    }

    public List<Movie> getFavMovies() {
        return favMovies;
    }

    public void setFavMovies(List<Movie> favMovies) {
        this.favMovies = favMovies;
    }

    public List<Actor> getFavActors() {
        return favActors;
    }

    public void setFavActors(List<Actor> favActors) {
        this.favActors = favActors;
    }

    public List<CrewMember> getFavDirectors() {
        return favDirectors;
    }

    public void setFavDirectors(List<CrewMember> favDirectors) {
        this.favDirectors = favDirectors;
    }

    public List<Genre> getFavGenres() {
        return favGenres;
    }

    public void setFavGenres(List<Genre> favGenres) {
        this.favGenres = favGenres;
    }

}
