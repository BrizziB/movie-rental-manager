package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;
import java.util.List;

public class LiteCharacterDto implements Serializable {

    private String name;
    private LiteActorDto actor;
    private List<LiteActorDto> actors;
    private List<LiteMovieDto> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LiteActorDto getActor() {
        return actor;
    }

    public void setActor(LiteActorDto actor) {
        this.actor = actor;
    }

    public List<LiteActorDto> getActors() {
        return actors;
    }

    public void setActors(List<LiteActorDto> actors) {
        this.actors = actors;
    }

    public List<LiteMovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<LiteMovieDto> movies) {
        this.movies = movies;
    }
}
