package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.DirectorDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteDirectorDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.DirectorMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.DirectorDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Director;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class DirectorController {

    @Inject private DirectorDao directorDao;
    @Inject private DirectorMapper directorMapper;
    @Inject private MovieDao movieDao;
    private Gson gson;

    public LiteDirectorDto getDirectorById(Long id) {
        Director director = directorDao.findById(id)
                                       .orElseThrow(
                                               () -> new IllegalArgumentException("Director not found")
                                       );
        return directorMapper.convert(director);
    }

    public LiteDirectorDto getDirectorByMovieId(Long id) {
        Movie movie = movieDao.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Movie not found")
        );
        return directorMapper.convert(movie.getDirector());
    }

    public List<LiteDirectorDto> getDirectorsByName(String name) {
        return directorDao.retrieveDirectorsByName(name)
                          .stream()
                          .map(directorMapper::convert)
                          .collect(Collectors.toList());
    }

    public List<LiteDirectorDto> getAllDirectors(Integer offset, Integer limit, List<String> orderBy) {
        return directorDao.findAll(offset, limit, orderBy)
                .stream()
                .map(directorMapper::convert)
                .collect(Collectors.toList());
    }

    public void addDirectorToDb(String json) {
        gson = new Gson();
        DirectorDto dto = gson.fromJson(json, DirectorDto.class);
        Director director = ModelFactory.initDirector();
        directorMapper.transfer(dto, director);
        directorDao.add(director);
    }

    public void updateDirectorOnDb(String json, Long id) {
        gson = new Gson();
        DirectorDto dto = gson.fromJson(json, DirectorDto.class);
        Director oldDirector = directorDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any director on database")
        );
        directorMapper.transfer(dto, oldDirector);
        directorDao.update(oldDirector);
    }

    public void disableDirectorOnDb(boolean disabled, Long id) {
        Director director = directorDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any director on database")
        );
        director.setDisabled(disabled);
        directorDao.save(director);
    }

}
