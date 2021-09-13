package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.ActorDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteActorDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.ActorMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.ActorDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ActorController {

    @Inject private ActorDao actorDao;
    @Inject private ActorMapper actorMapper;
    @Inject private MovieDao movieDao;
    private Gson gson;

    public LiteActorDto getActorById(Long id) {
        Actor actor = actorDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Actor not found")
        );
        return actorMapper.convert(actor);
    }

    public List<LiteActorDto> getCastByMovieId(Long id) {
        Movie movie = movieDao.fetchMovieWithCast(id);
        return movie.getCast()
                    .stream()
                    .map(actorMapper::convert)
                    .collect(Collectors.toList());
    }

    public List<LiteActorDto> getActorsByName(String name) {
        return actorDao.retrieveActorsByName(name)
                       .stream()
                       .map(actorMapper::convert)
                       .collect(Collectors.toList());
    }

    public List<LiteActorDto> getAllActors(Integer offset, Integer limit) {
        return actorDao.findAll(offset, limit)
                .stream()
                .map(actorMapper::convert)
                .collect(Collectors.toList());
    }

    public void addActorToDb(String json) {
        gson = new Gson();
        ActorDto dto = gson.fromJson(json, ActorDto.class);
        Actor actor = ModelFactory.initActor();
        actorMapper.transfer(dto, actor);
        actorDao.add(actor);
    }

    public void updateActorOnDb(String json, Long id) {
        gson = new Gson();
        ActorDto dto = gson.fromJson(json, ActorDto.class);
        Actor oldActor = actorDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any actor on database")
        );
        actorMapper.transfer(dto, oldActor);
        actorDao.update(oldActor);
    }

    public void disableActorOnDb(boolean disabled, Long id) {
        Actor actor = actorDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any actor on database")
        );
        actor.setDisabled(disabled);
        actorDao.save(actor);
    }

}
