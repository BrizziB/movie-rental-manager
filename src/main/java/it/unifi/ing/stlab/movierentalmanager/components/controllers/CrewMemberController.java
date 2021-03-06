package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.CrewMemberDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteCrewMemberDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.CrewMemberMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.CrewMemberDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.CrewMember;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class CrewMemberController {

    @Inject private CrewMemberDao crewMemberDao;
    @Inject private CrewMemberMapper crewMemberMapper;
    @Inject private MovieDao movieDao;
    private Gson gson;

    public LiteCrewMemberDto getCrewMemberById(Long id) {
        CrewMember crewMember = crewMemberDao.findById(id)
                                             .orElseThrow(
                                                     () -> new IllegalArgumentException("Crew member not found")
                                             );
        return crewMemberMapper.convert(crewMember);
    }

    public List<LiteCrewMemberDto> getCrewMembersByMovieId(Long id) {
        Movie movie = movieDao.fetchMovieWithCrewMembers(id);
        return movie.getCrew()
                .stream()
                .map(crewMemberMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteCrewMemberDto> getCrewMembersByName(String name) {
        return crewMemberDao.retrieveCrewMembersByName(name)
                            .stream()
                            .map(crewMemberMapper::convert)
                            .collect(Collectors.toList());
    }

    public List<LiteCrewMemberDto> getAllCrewMembers(Integer offset, Integer limit, List<String> orderBy) {
        return crewMemberDao.findAll(offset, limit, orderBy)
                .stream()
                .map(crewMemberMapper::convert)
                .collect(Collectors.toList());
    }

    public void addCrewMemberToDb(String json) {
        gson = new Gson();
        CrewMemberDto dto = gson.fromJson(json, CrewMemberDto.class);
        CrewMember crewMember = ModelFactory.initCrewMember();
        crewMemberMapper.transfer(dto, crewMember);
        crewMemberDao.add(crewMember);
    }

    public void updateCrewMemberOnDb(String json, Long id) {
        gson = new Gson();
        CrewMemberDto dto = gson.fromJson(json, CrewMemberDto.class);
        CrewMember oldCrewMember = crewMemberDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any crew member on database")
        );
        crewMemberMapper.transfer(dto, oldCrewMember);
        crewMemberDao.update(oldCrewMember);
    }

    public void disableCrewMemberOnDb(boolean disabled, Long id) {
        CrewMember crewMember = crewMemberDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any crew member on database")
        );
        crewMember.setDisabled(disabled);
        crewMemberDao.save(crewMember);
    }

}
