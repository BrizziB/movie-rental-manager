package it.unifi.ing.stlab.movierentalmanager.controller.beans;


import it.unifi.ing.stlab.movierentalmanager.controller.factory.ModelFactory;
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

        CrewMember regista = ModelFactory.initCrewMember();
        regista.setName("Renè");
        regista.setSurname("Ferretti");

        CrewMember direttoreFotografia = ModelFactory.initCrewMember();
        direttoreFotografia.setName("Duccio");
        direttoreFotografia.setSurname("Patanè");

        Actor protagonista = ModelFactory.initActor();
        protagonista.setName("Stanis");
        protagonista.setSurname("LaRochelle");

        Character character0 = ModelFactory.initCharacter();
        character0.setName("Ennio Battaglia");
        character0.setActor(protagonista);

        Movie movie = ModelFactory.initMovie();
        movie.setTitle("Poffarbacco all'arrembaggio");
        movie.setDirector(regista);
        movie.getCrew().add(direttoreFotografia);
        movie.getCast().add(protagonista);
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
