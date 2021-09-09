package it.unifi.ing.stlab.movierentalmanager.components.beans;


import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.Discounter;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.Order;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class TestStartupBean {

    @PersistenceContext
    EntityManager em;

//    @PostConstruct /* commentare per non farlo eseguire */
    @Transactional
    public void init(){
        System.out.println("Eseguo");

        // TEST DISCOUNTER
//        PhysicalMovieItem pmi = ModelFactory.initPhysicalMovieItem();
//        pmi.setRentalPrice(BigDecimal.valueOf(25.00));
//
//        Order order = ModelFactory.initOrder();
//        List<Discounter> discounters = new ArrayList<Discounter>();
//        discounters.add(Discounter.blackFridayDiscounter());
//        discounters.add(Discounter.premiumDiscounter());
//        order.setDiscounters(discounters);
//        order.getItems().add(pmi);
//        order.computeDiscountedTotal();
//        System.out.println( "Discounted total: " + order.getDiscountedTotal() );

        // DIRECTORS
//        Director tarantinoDr = ModelFactory.initDirector();
//        tarantinoDr.setName("Quentin Tarantino");

        // CINEMATOGRAPHERS
//        CrewMember sekula = ModelFactory.initCrewMember();
//        sekula.setName("Andrzej Sekula");
//        sekula.setRole(CrewRole.CINEMATOGRAPHER);
//
//        CrewMember richardson = ModelFactory.initCrewMember();
//        richardson.setName("Robert Richardson");
//        richardson.setRole(CrewRole.CINEMATOGRAPHER);

        // WRITER
//        CrewMember tarantinoWr = ModelFactory.initCrewMember();
//        tarantinoWr.setName("Quentin Tarantino");
//        tarantinoWr.setRole(CrewRole.WRITER);


        // ACTORS
//        Actor travolta = ModelFactory.initActor();
//        travolta.setName("John Travolta");
//
//        Actor thurman = ModelFactory.initActor();
//        thurman.setName("Uma Thurman");
//
//        Actor willis = ModelFactory.initActor();
//        willis.setName("Bruce Willis");
//
//        Actor liu = ModelFactory.initActor();
//        liu.setName("Lucy Liu");

        // MOVIES
//        Movie pulp = ModelFactory.initMovie();
//        pulp.setTitle("Pulp Fiction");
//        pulp.setDirector(tarantinoDr);
//        pulp.getCrew().add(sekula);
//        pulp.getCrew().add(tarantinoWr);
//        pulp.getCast().add(travolta);
//        pulp.getCast().add(thurman);
//        pulp.getCast().add(willis);
//        pulp.setLanguage("English");
//        pulp.setGenre(Genre.ACTION);
//        pulp.setCriticRating(4.2);
//
//        Movie billVol1 = ModelFactory.initMovie();
//        billVol1.setTitle("Kill Bill: Vol.1");
//        billVol1.setDirector(tarantinoDr);
//        billVol1.getCrew().add(richardson);
//        billVol1.getCast().add(thurman);
//        billVol1.getCast().add(liu);
//        billVol1.setLanguage("English");
//        billVol1.setGenre(Genre.ACTION);

        /*em.getTransaction().begin();*/
//        em.persist(tarantinoDr);
//        em.persist(sekula);
//        em.persist(richardson);
//        em.persist(tarantinoWr);
//        em.persist(travolta);
//        em.persist(thurman);
//        em.persist(willis);
//        em.persist(liu);
//        em.persist(pulp);
//        em.persist(billVol1);
//        em.persist(pmi);
//        em.persist(order);
        /*em.getTransaction().commit();*/

    }

}
