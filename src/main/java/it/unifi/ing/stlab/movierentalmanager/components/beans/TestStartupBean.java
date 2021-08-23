package it.unifi.ing.stlab.movierentalmanager.components.beans;


import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.*;
import it.unifi.ing.stlab.movierentalmanager.model.Character;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
@Startup
public class TestStartupBean {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    @Transactional
    public void init(){
        System.out.println("Eseguo");

        CrewMember cm1 = ModelFactory.initCrewMember();
        cm1.setName("Renè");
        cm1.setSurname("Ferretti");
        cm1.setRole("Regista");

        CrewMember cm2 = ModelFactory.initCrewMember();
        cm2.setName("Duccio");
        cm2.setSurname("Patanè");
        cm2.setRole("Direttore della fotografia");

        Actor a1 = ModelFactory.initActor();
        a1.setName("Stanis");
        a1.setSurname("LaRochelle");

        Character c1 = ModelFactory.initCharacter();
        c1.setName("Ennio Battaglia");
        c1.setActor(a1);

        Movie m1 = ModelFactory.initMovie();
        m1.setTitle("Poffarbacco all'arrembaggio");
        m1.setDirector(cm1);
        m1.getCrew().add(cm2);
        m1.getCast().add(a1);
        m1.getCharacters().add(c1);

        /*em.getTransaction().begin();*/
        em.persist(cm1);
        em.persist(cm2);
        em.persist(a1);
        em.persist(c1);
        em.persist(m1);
        /*em.getTransaction().commit();*/

    }


}
