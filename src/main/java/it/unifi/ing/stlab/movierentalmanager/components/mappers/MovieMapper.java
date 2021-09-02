package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.Director;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class MovieMapper {

    @Inject private DirectorMapper directorMapper;

    public LiteMovieDto convert(Movie m) {
        if(m == null)
            throw new MapperConversionException("The movie is NULL");

        LiteMovieDto dto = new LiteMovieDto();

        dto.setTitle(m.getTitle());
        dto.setYear(m.getYear());
        dto.setLength(m.getLength());
        dto.setPlot(m.getPlot());
        dto.setCountry(m.getCountry());
        dto.setLanguage(m.getLanguage());
        dto.setBudget(m.getBudget());
        dto.setCriticRating(m.getCriticRating());
        dto.setDirector( directorMapper.convert(m.getDirector()) );
        dto.setGenre(m.getGenre());
        dto.setRating(m.getRating());

        return dto;
    }

    public void transfer(LiteMovieDto dto, Movie m) {
        if(dto == null)
            throw new MapperTransferException("The movie DTO is NULL");
        if(m == null)
            throw new MapperTransferException("The movie Entity is NULL");

        if(dto.getTitle() != null)
            m.setTitle(dto.getTitle());
        if(dto.getYear() != null)
            m.setYear(dto.getYear());
        if(dto.getLength() != null)
            m.setLength(dto.getLength());
        if(dto.getPlot() != null)
            m.setPlot(dto.getPlot());
        if(dto.getCountry() != null)
            m.setCountry(dto.getCountry());
        if(dto.getLanguage() != null)
            m.setLanguage(dto.getLanguage());
        if(dto.getBudget() != null)
            m.setBudget(dto.getBudget());
        if(dto.getCriticRating() != null)
            m.setCriticRating(dto.getCriticRating());
        if(dto.getGenre() != null)
            m.setGenre(dto.getGenre());
        if(dto.getRating() != null)
            m.setRating(dto.getRating());
        if(dto.getDirector() != null) {
            Director director = ModelFactory.initDirector();
            directorMapper.transfer(dto.getDirector(), director);
            m.setDirector(director);
        }
    }

}
