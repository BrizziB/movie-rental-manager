package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCrewMemberDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
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
        serializeMovies(dto, cm.getMovies());

        return dto;
    }

    public void transfer(LiteCrewMemberDto dto, CrewMember cm) {
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
        if(dto.getMovies() != null)
            deSerializeMovies(cm, dto.getMovies());
    }

    private void serializeMovies(LiteCrewMemberDto dto, List<Movie> movies) {
        if(movies != null && movies.size() > 0)
            for (Movie m : movies)
                dto.getMovies().add( movieMapper.convert(m) );
    }

    private void deSerializeMovies(CrewMember cm, List<LiteMovieDto> liteMovies) {
        cm.getMovies().clear();

        if(liteMovies != null && liteMovies.size() > 0)
            for (LiteMovieDto liteMovie : liteMovies) {
                if ( movieDao.retrieveMoviesByTitle( liteMovie.getTitle() ).size() != 0 )
                    System.out.println("Movies with similar names do exist in database. Do you want to check them out?");
                else {
                    Movie movie = ModelFactory.initMovie();
                    movieMapper.transfer(liteMovie, movie);
                    movieDao.add(movie);
                    cm.getMovies().add(movie);
                }
            }
    }

}
