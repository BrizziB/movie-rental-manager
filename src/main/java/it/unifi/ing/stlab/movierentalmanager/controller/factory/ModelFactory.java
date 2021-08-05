package it.unifi.ing.stlab.movierentalmanager.controller.factory;

import it.unifi.ing.stlab.movierentalmanager.model.BaseEntity;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.Person;
import it.unifi.ing.stlab.movierentalmanager.model.Character;

import java.util.UUID;

public class ModelFactory {

    public static Movie initMovie(){
        Movie movie = new Movie(UUID.randomUUID());
        return movie;
    }

    public static Person initPerson(){
        Person person = new Person(UUID.randomUUID());
        return person;
    }

    public static Character initCharacter(){
        Character character = new Character(UUID.randomUUID());
        return character;
    }


}
