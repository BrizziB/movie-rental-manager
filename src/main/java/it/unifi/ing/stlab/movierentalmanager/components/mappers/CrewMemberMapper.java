package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.CrewMemberDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.MovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteCrewMemberDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.CrewMemberDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.CrewMember;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class CrewMemberMapper {

    @Inject private CrewMemberDao dao;
    @Inject private MovieDao movieDao;
    @Inject private MovieMapper movieMapper;

    public LiteCrewMemberDto convert(CrewMember cm) {
        if(cm == null)
            throw new MapperConversionException("The crew member is NULL");

        LiteCrewMemberDto dto = new LiteCrewMemberDto();

        dto.setName(cm.getName());
        dto.setBirthDate(cm.getBirthDate());
        dto.setCountry(cm.getCountry());
        dto.setRole(cm.getRole());
//        serializeMovies(dto, cm.getMovies());

        return dto;
    }

    public void transfer(CrewMemberDto dto, CrewMember cm) {
        if(dto == null)
            throw new MapperTransferException("The crew member DTO is NULL");
        if(cm == null)
            throw new MapperTransferException("The crew member Entity is NULL");

        if(dto.getName() != null)
            cm.setName(dto.getName());
        if(dto.getBirthDate() != null)
            cm.setBirthDate(dto.getBirthDate());
        if(dto.getCountry() != null)
            cm.setCountry(dto.getCountry());
        if(dto.getRole() != null)
            cm.setRole(dto.getRole());
        if(dto.getMoviesIDs() != null)
            deSerializeMovies(cm, dto.getMoviesIDs());
    }

//    private void serializeMovies(LiteCrewMemberDto dto, List<Movie> movies) {
//        if(movies != null && movies.size() > 0)
//            for (Movie m : movies)
//                dto.getMovies().add( movieMapper.convert(m) );
//    }

    private void deSerializeMovies(CrewMember cm, List<Long> moviesIDs) {
        cm.getMovies().clear();

        if(moviesIDs != null || moviesIDs.size() > 0) {
            for (Long ID : moviesIDs) {
                cm.getMovies().add(
                        movieDao.findById(ID)
                                .orElseThrow(() -> new IllegalArgumentException("Movie not found"))
                );
            }
        }
    }

}
