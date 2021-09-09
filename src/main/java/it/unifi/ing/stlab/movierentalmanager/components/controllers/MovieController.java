package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.MovieMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.ActorDao;
import it.unifi.ing.stlab.movierentalmanager.dao.CrewMemberDao;
import it.unifi.ing.stlab.movierentalmanager.dao.DirectorDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class MovieController {

    @Inject private ActorDao actorDao;
    @Inject private CrewMemberDao crewMemberDao;
    @Inject private DirectorDao directorDao;
    @Inject private MovieDao movieDao;
    @Inject private MovieMapper movieMapper;
    private Gson gson;

    public LiteMovieDto getMovieById(Long id) {
        Movie movie = movieDao.findById(id)
                              .orElseThrow(
                                      () -> new IllegalArgumentException("Movie not found")
                              );
        return movieMapper.convert(movie);
    }

    public List<LiteMovieDto> getMoviesByActorId(Long id) {
        Actor actor = actorDao.fetchActorWithMovies(id);
        return actor.getMovies()
                    .stream()
                    .map(movieMapper::convert)
                    .collect(Collectors.toList());
    }

    public List<LiteMovieDto> getMoviesByDirectorId(Long id) {
        Director director = directorDao.fetchDirectorWithMovies(id);
        return director.getMovies()
                       .stream()
                       .map(movieMapper::convert)
                       .collect(Collectors.toList());
    }

    public List<LiteMovieDto> getMoviesByCinematographerId(Long id) {
        CrewMember crewMember = crewMemberDao.fetchCrewMemberWithMovies(id, CrewRole.CINEMATOGRAPHER);
        return crewMember.getMovies()
                         .stream()
                         .map(movieMapper::convert)
                         .collect(Collectors.toList());
    }

    public List<LiteMovieDto> getMoviesByWriterId(Long id) {
        CrewMember crewMember = crewMemberDao.fetchCrewMemberWithMovies(id, CrewRole.WRITER);
        return crewMember.getMovies()
                         .stream()
                         .map(movieMapper::convert)
                         .collect(Collectors.toList());
    }

    public List<LiteMovieDto> getMoviesByTitle(String title) {
        return movieDao.retrieveMoviesByTitle(title)
                       .stream()
                       .map(movieMapper::convert)
                       .collect(Collectors.toList());
    }

    public List<LiteMovieDto> getAllMovies(Integer offset, Integer limit) {
        return movieDao.findAll(offset, limit)
                .stream()
                .map(movieMapper::convert)
                .collect(Collectors.toList());
    }

    public void addMovieToDb(String json) {
        gson = new Gson();
        LiteMovieDto dto = gson.fromJson(json, LiteMovieDto.class);
        Movie movie = ModelFactory.initMovie();
        movieMapper.transfer(dto, movie);
        movieDao.add(movie);
    }

    public void updateMovieOnDb(String json, Long id) {
        gson = new Gson();
        LiteMovieDto dto = gson.fromJson(json, LiteMovieDto.class);
        Movie oldMovie = movieDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any movie on database")
        );
        movieMapper.transfer(dto, oldMovie);
        movieDao.update(oldMovie);
    }

    public void disableMovieOnDb(boolean disabled, Long id) {
        Movie movie = movieDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any movie on database")
        );
        movie.setDisabled(disabled);
        movieDao.save(movie);
    }

}