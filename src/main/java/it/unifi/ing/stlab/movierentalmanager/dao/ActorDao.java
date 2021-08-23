package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ActorDao extends BaseDao<Actor> {

    public ActorDao() {
        super(Actor.class);
    }

    @Transactional
    public void update(Actor a) {
        Actor oldActor = getEm().find(Actor.class, a.getId());
        oldActor.setName(a.getName());
        oldActor.setSurname(a.getSurname());
        oldActor.setBirthDate(a.getBirthDate());
        oldActor.setCountry(a.getCountry());
        oldActor.setBiography(a.getBiography());
        oldActor.setMovies(a.getMovies());
        oldActor.setCharacters(a.getCharacters());
        oldActor.setStageName(a.getStageName());
    }

    @Transactional
    public Actor fetchActorWithMovies(Long id) {
        TypedQuery<Actor> query = getEm().createQuery("FROM Actor a JOIN FETCH a.movies WHERE a.id = :id", Actor.class)
                                         .setParameter("id", id);
        return query.getSingleResult();
    }

}
