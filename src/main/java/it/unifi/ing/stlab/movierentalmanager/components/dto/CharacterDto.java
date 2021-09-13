package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;
import java.util.List;

public class CharacterDto implements Serializable {

    private String name;
    private ActorDto actor;
    private List<ActorDto> actors;
    private List<MovieDto> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActorDto getActor() {
        return actor;
    }

    public void setActor(ActorDto actor) {
        this.actor = actor;
    }

    public List<ActorDto> getActors() {
        return actors;
    }

    public void setActors(List<ActorDto> actors) {
        this.actors = actors;
    }

    public List<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDto> movies) {
        this.movies = movies;
    }
}
