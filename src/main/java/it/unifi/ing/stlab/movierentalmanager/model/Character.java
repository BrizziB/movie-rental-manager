package it.unifi.ing.stlab.movierentalmanager.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity {

    private String name;
    @ManyToOne private Actor actor;

    @ManyToMany
    @JoinTable(name="characters_actors",
            joinColumns=@JoinColumn(name="character_id"),
            inverseJoinColumns=@JoinColumn(name="actor_id"))
    private List<Actor> actors;

    @ManyToMany(mappedBy = "characters")
    private List<Movie> movies;


    public Character(){
        super();
        actors = new ArrayList<Actor>();
        movies = new ArrayList<Movie>();
    }

    public Character(UUID uuid){
        super(uuid);
        actors = new ArrayList<Actor>();
        movies = new ArrayList<Movie>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
