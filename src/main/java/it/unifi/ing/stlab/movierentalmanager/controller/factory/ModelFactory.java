package it.unifi.ing.stlab.movierentalmanager.controller.factory;

import it.unifi.ing.stlab.movierentalmanager.model.*;
import it.unifi.ing.stlab.movierentalmanager.model.Character;

import java.util.UUID;

public class ModelFactory {

    public static Movie initMovie(){
        Movie movie = new Movie(UUID.randomUUID());
        return movie;
    }

    public static Actor initActor(){
        Actor actor = new Actor(UUID.randomUUID());
        return actor;
    }

    public static CrewMember initCrewMember(){
        CrewMember director = new CrewMember(UUID.randomUUID());
        return director;
    }

    public static Character initCharacter(){
        Character character = new Character(UUID.randomUUID());
        return character;
    }

}
