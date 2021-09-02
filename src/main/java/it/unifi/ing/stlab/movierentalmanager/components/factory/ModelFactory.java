package it.unifi.ing.stlab.movierentalmanager.components.factory;

import it.unifi.ing.stlab.movierentalmanager.model.*;
import it.unifi.ing.stlab.movierentalmanager.model.Character;

import java.util.UUID;

public class ModelFactory {

    public static Actor initActor() {
        return new Actor(UUID.randomUUID());
    }

    public static Character initCharacter() {
        return new Character(UUID.randomUUID());
    }

    public static CrewMember initCrewMember() {
        return new CrewMember(UUID.randomUUID());
    }

    public static Customer initCustomer() {
        return new Customer(UUID.randomUUID());
    }

    public static DigitalMovieItem initDigitalMovieItem() {
        return new DigitalMovieItem(UUID.randomUUID());
    }

    public static Director initDirector() {
        return new Director(UUID.randomUUID());
    }

    public static Employee initEmployee() {
        return new Employee(UUID.randomUUID());
    }

    public static Movie initMovie() {
        return new Movie(UUID.randomUUID());
    }

    public static Order initOrder() {
        return new Order(UUID.randomUUID());
    }

    public static PaymentProfile initPaymentProfile() {
        return new PaymentProfile(UUID.randomUUID());
    }

    public static PhysicalMovieItem initPhysicalMovieItem() {
        return new PhysicalMovieItem(UUID.randomUUID());
    }

    public static ProductionCompany initProductionCompany() {
        return new ProductionCompany(UUID.randomUUID());
    }

}
