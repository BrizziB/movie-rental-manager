package it.unifi.ing.stlab.movierentalmanager.components.beans;


import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.WeeklyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.movies.*;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.Order;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.OrderStatus;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.YearlyRecord;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Singleton
@Startup
public class TestStartupBean {

    @PersistenceContext
    EntityManager em;

//    @PostConstruct /* commentare per non farlo eseguire */
    @Transactional
    public void init(){
        System.out.println("Eseguo");

        // DIRECTORS
        Director tarantinoDr = ModelFactory.initDirector();
        tarantinoDr.setName("Quentin Tarantino");

        // CINEMATOGRAPHERS
        CrewMember sekula = ModelFactory.initCrewMember();
        sekula.setName("Andrzej Sekula");
        sekula.setRole(CrewRole.CINEMATOGRAPHER);

        CrewMember richardson = ModelFactory.initCrewMember();
        richardson.setName("Robert Richardson");
        richardson.setRole(CrewRole.CINEMATOGRAPHER);

        // WRITER
        CrewMember tarantinoWr = ModelFactory.initCrewMember();
        tarantinoWr.setName("Quentin Tarantino");
        tarantinoWr.setRole(CrewRole.WRITER);


        // ACTORS
        Actor travolta = ModelFactory.initActor();
        travolta.setName("John Travolta");

        Actor thurman = ModelFactory.initActor();
        thurman.setName("Uma Thurman");

        Actor willis = ModelFactory.initActor();
        willis.setName("Bruce Willis");

        Actor liu = ModelFactory.initActor();
        liu.setName("Lucy Liu");

        // MOVIES
        Movie pulp = ModelFactory.initMovie();
        pulp.setTitle("Pulp Fiction");
        pulp.setDirector(tarantinoDr);
        pulp.getCrew().add(sekula);
        pulp.getCrew().add(tarantinoWr);
        pulp.getCast().add(travolta);
        pulp.getCast().add(thurman);
        pulp.getCast().add(willis);
        pulp.setLanguage("English");
        pulp.setGenre(Genre.ACTION);
        pulp.setCriticRating(4.2);

        Movie billVol1 = ModelFactory.initMovie();
        billVol1.setTitle("Kill Bill: Vol.1");
        billVol1.setDirector(tarantinoDr);
        billVol1.getCrew().add(richardson);
        billVol1.getCast().add(thurman);
        billVol1.getCast().add(liu);
        billVol1.setLanguage("English");
        billVol1.setGenre(Genre.ACTION);

        // TEST DISCOUNTER
        PhysicalMovieItem pmi1 = ModelFactory.initPhysicalMovieItem();
        pmi1.setMovie(pulp);
        pmi1.setRentalPrice(BigDecimal.valueOf(25.00));

        PhysicalMovieItem pmi2 = ModelFactory.initPhysicalMovieItem();
        pmi2.setMovie(pulp);
        pmi2.setRentalPrice(BigDecimal.valueOf(25.00));

        Order order = ModelFactory.initOrder();
//        List<Discounter> discounters = new ArrayList<Discounter>();
//        discounters.add(Discounter.blackFridayDiscounter());
//        discounters.add(Discounter.premiumDiscounter());
//        order.setDiscounters(discounters);
        order.getItems().add(pmi1);
        order.getItems().add(pmi2);
        order.computeDiscountedTotal();
        order.setOrderStatus(OrderStatus.CLOSED);
//        System.out.println( "Discounted total: " + order.getDiscountedTotal() );
        WeeklyRecord wRecord1 = ModelFactory.initWeeklyRecord(pmi1.getMovie(), pmi1.getMovie().getThisWeekPurchases(), pmi1.getMovie().getTotalPurchases());
        MonthlyRecord mRecord1 = ModelFactory.initMonthlyRecord(pmi1.getMovie(), pmi1.getMovie().getThisMonthPurchases(), pmi1.getMovie().getTotalPurchases());
        YearlyRecord yRecord1 = ModelFactory.initYearlyRecord(pmi1.getMovie(), pmi1.getMovie().getThisYearPurchases(), pmi1.getMovie().getTotalPurchases());

        /*em.getTransaction().begin();*/
        em.persist(tarantinoDr);
        em.persist(sekula);
        em.persist(richardson);
        em.persist(tarantinoWr);
        em.persist(travolta);
        em.persist(thurman);
        em.persist(willis);
        em.persist(liu);
        em.persist(pulp);
        em.persist(billVol1);
        em.persist(pmi1);
        em.persist(pmi2);
        em.persist(order);
        em.persist(wRecord1);
        em.persist(mRecord1);
        em.persist(yRecord1);
        /*em.getTransaction().commit();*/

    }

}
