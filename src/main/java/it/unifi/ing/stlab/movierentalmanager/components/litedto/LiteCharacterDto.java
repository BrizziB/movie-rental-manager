package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import java.io.Serializable;
import java.util.List;

public class LiteCharacterDto implements Serializable {

    private String name;
    private LiteActorDto actor;

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

}
