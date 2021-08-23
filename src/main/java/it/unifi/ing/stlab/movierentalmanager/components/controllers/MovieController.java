package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import it.unifi.ing.stlab.movierentalmanager.dao.ActorDao;
import it.unifi.ing.stlab.movierentalmanager.model.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class MovieController {

    @Inject private ActorDao actorDao;

    public List<Movie> getMoviesByActor(Long id) {
        Actor actor = actorDao.fetchActorWithMovies(id);
        return actor.getMovies();
    }


}
