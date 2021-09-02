package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteDigitalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.DigitalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class DigitalMovieItemMapper {

    @Inject private MovieMapper movieMapper;

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

    public void transfer(LiteDigitalMovieItemDto dto, DigitalMovieItem dmi) {
        if(dto == null)
            throw new MapperTransferException("The digital movie item DTO is NULL");
        if(dmi == null)
            throw new MapperTransferException("The digital movie item Entity is NULL");

        if(dto.getMovie() != null) {
            Movie movie = ModelFactory.initMovie();
            movieMapper.transfer(dto.getMovie(), movie);
            dmi.setMovie(movie);
        }
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
