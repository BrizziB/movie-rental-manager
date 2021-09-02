package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "actors")
@DiscriminatorValue("actor")
public class Actor extends Person {

    @ManyToMany(mappedBy = "cast")
    private List<Movie> movies;

    @ManyToMany(mappedBy = "actors")
    private List<Character> characters;

    private String stageName;

    public Actor() {
        super();
        movies = new ArrayList<Movie>();
        characters = new ArrayList<Character>();
    }

    public Actor(UUID uuid) {
        super(uuid);
        movies = new ArrayList<Movie>();
        characters = new ArrayList<Character>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

}
