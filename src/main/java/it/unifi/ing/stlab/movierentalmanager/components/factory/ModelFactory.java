package it.unifi.ing.stlab.movierentalmanager.components.factory;

import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.WeeklyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.items.DigitalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.movies.*;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Character;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.Order;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.PaymentProfile;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.YearlyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;
import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;

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

    public static WeeklyRecord initWeeklyRecord(Movie movie, Integer thisWeekPurchases, Long currentTotalPurchases) {
        return new WeeklyRecord(UUID.randomUUID(), movie, thisWeekPurchases, currentTotalPurchases);
    }

    public static MonthlyRecord initMonthlyRecord(Movie movie, Integer thisMonthPurchases, Long currentTotalPurchases) {
        return new MonthlyRecord(UUID.randomUUID(), movie, thisMonthPurchases, currentTotalPurchases);
    }

    public static YearlyRecord initYearlyRecord(Movie movie, Integer thisYearPurchases, Long currentTotalPurchases) {
        return new YearlyRecord(UUID.randomUUID(), movie, thisYearPurchases, currentTotalPurchases);
    }

}
