package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteDirectorDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
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
            throw new MapperConversionException("The director is NULL");

        LiteDirectorDto dto = new LiteDirectorDto();

        dto.setName(d.getName());
        dto.setBirthDate(d.getBirthDate());
        dto.setCountry(d.getCountry());
        serializeMovies(dto, d.getMovies());

        return dto;
    }

    public void transfer(LiteDirectorDto dto, Director d) {
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
        if(dto.getMovies() != null)
            deSerializeMovies(d, dto.getMovies());
    }

    private void serializeMovies(LiteDirectorDto dto, List<Movie> movies) {
        if(movies != null && movies.size() > 0)
            for (Movie m : movies)
                dto.getMovies().add(movieMapper.convert(m) );
    }

    private void deSerializeMovies(Director d, List<LiteMovieDto> liteMovies) {
        d.getMovies().clear();

        if(liteMovies != null && liteMovies.size() > 0)
            for (LiteMovieDto liteMovie : liteMovies) {
                if ( movieDao.retrieveMoviesByTitle(liteMovie.getTitle()).size() != 0)
                    System.out.println("Movies with similar titles do exist in database. Do you want to check them out?");
                else {
                    Movie movie = ModelFactory.initMovie();
                    movieMapper.transfer(liteMovie, movie);
                    movieDao.add(movie);
                    d.getMovies().add(movie);
                }
            }
    }

}
