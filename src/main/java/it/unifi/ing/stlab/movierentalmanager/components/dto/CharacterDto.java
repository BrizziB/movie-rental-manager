package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;
import java.util.List;

public class CharacterDto {

    private String name;
    private ActorDto actor;
    private List<Long> moviesIDs;
    private List<Long> actorsIDs;

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

    public List<Long> getMoviesIDs() {
        return moviesIDs;
    }

    public void setMoviesIDs(List<Long> moviesIDs) {
        this.moviesIDs = moviesIDs;
    }

    public List<Long> getActorsIDs() {
        return actorsIDs;
    }

    public void setActorsIDs(List<Long> actorsIDs) {
        this.actorsIDs = actorsIDs;
    }
}
