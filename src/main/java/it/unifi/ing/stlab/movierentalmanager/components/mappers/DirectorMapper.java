package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.DirectorDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.MovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteDirectorDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Director;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class DirectorMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;

    public LiteDirectorDto convert(Director d) {
        if(d == null)
            return new LiteDirectorDto();
//            throw new MapperConversionException("The director is NULL");

        LiteDirectorDto dto = new LiteDirectorDto();

        dto.setName(d.getName());
        dto.setBirthDate(d.getBirthDate());
        dto.setCountry(d.getCountry());
//        serializeMovies(dto, d.getMovies());

        return dto;
    }

    public void transfer(DirectorDto dto, Director d) {
        if(dto == null)
            throw new MapperTransferException("The director DTO is NULL");
        if(d == null)
            throw new MapperTransferException("The director Entity is NULL");

        if(dto.getName() != null)
            d.setName(dto.getName());
        if(dto.getBirthDate() != null)
            d.setBirthDate(dto.getBirthDate());
        if(dto.getCountry() != null)
            d.setCountry(dto.getCountry());
        if(dto.getMoviesIDs() != null)
            deSerializeMovies(d, dto.getMoviesIDs());
    }

//    private void serializeMovies(LiteDirectorDto dto, List<Movie> movies) {
//        if(movies != null && movies.size() > 0)
//            for (Movie m : movies)
//                dto.getMovies().add(movieMapper.convert(m) );
//    }

    private void deSerializeMovies(Director d, List<Long> moviesIDs) {
        d.getMovies().clear();

        if(moviesIDs != null || moviesIDs.size() > 0) {
            for (Long ID : moviesIDs) {
                d.getMovies().add(
                        movieDao.findById(ID)
                                .orElseThrow(() -> new IllegalArgumentException("Movie not found"))
                );
            }
        }
    }

}
