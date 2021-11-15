package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.DigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteDigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.items.DigitalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class DigitalMovieItemMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;

    public LiteDigitalMovieItemDto convert(DigitalMovieItem dmi) {
        if(dmi == null)
            throw new MapperConversionException("The digital movie item is NULL");

        LiteDigitalMovieItemDto dto = new LiteDigitalMovieItemDto();

        dto.setMovie(movieMapper.convert(dmi.getMovie()));
        dto.setRentalPrice(dmi.getRentalPrice());
        dto.setDiscountedPrice(dmi.getDiscountedPrice());
        dto.setUrl(dmi.getUrl());
        dto.setExpirationDate(dmi.getExpirationDate());

        return dto;
    }

    public void transfer(DigitalMovieItemDto dto, DigitalMovieItem dmi) {
        if(dto == null)
            throw new MapperTransferException("The digital movie item DTO is NULL");
        if(dmi == null)
            throw new MapperTransferException("The digital movie item Entity is NULL");

        dmi.setMovie(
                movieDao.findById( dto.getMovieID() )
                        .orElseThrow( () -> new IllegalArgumentException("Movie not found"))
        );
        if(dto.getRentalPrice() != null)
            dmi.setRentalPrice(dto.getRentalPrice());
        if(dto.getDiscountedPrice() != null)
            dmi.setDiscountedPrice(dto.getDiscountedPrice());
        if(dto.getUrl() != null)
            dmi.setUrl(dto.getUrl());
        if(dto.getExpirationDate() != null)
            dmi.setExpirationDate(dto.getExpirationDate());
    }

}
