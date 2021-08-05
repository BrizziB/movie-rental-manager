package it.unifi.ing.stlab.movierentalmanager.controller.beans;


import it.unifi.ing.stlab.movierentalmanager.controller.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.Character;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.Person;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;

@Singleton
@Startup
public class TestStartupBean {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    @Transactional
    public void init(){
        System.out.println("Eseguo");

        Person regista = ModelFactory.initPerson();
        regista.setName("Renè");
        regista.setSurname("Ferretti");

        Person direttoreFotografia = ModelFactory.initPerson();
        direttoreFotografia.setName("Duccio");
        direttoreFotografia.setSurname("Patanè");

        Person protagonista = ModelFactory.initPerson();
        protagonista.setName("Stanis");
        protagonista.setSurname("LaRochelle");

        Character character0 = ModelFactory.initCharacter();
        character0.setName("Ennio Battaglia");
        character0.setActor(protagonista);

        Movie movie = ModelFactory.initMovie();
        movie.setTitle("Diocane all'arrembaggio");
        movie.setDirector(regista);
        movie.getCrew().add(direttoreFotografia);
        movie.getCrew().add(protagonista);
        movie.getCharacters().add(character0);




        /*em.getTransaction().begin();*/
        em.persist(regista);
        em.persist(direttoreFotografia);
        em.persist(protagonista);
        em.persist(character0);
        em.persist(movie);
        /*em.getTransaction().commit();*/

    }


}
