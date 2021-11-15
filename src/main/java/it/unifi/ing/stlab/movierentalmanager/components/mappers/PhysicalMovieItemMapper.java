package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.PhysicalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LitePhysicalMovieItemDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PhysicalMovieItemMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;

    public LitePhysicalMovieItemDto convert(PhysicalMovieItem pmi) {
        if(pmi == null)
            throw new MapperConversionException("The physical movie item is NULL");

        LitePhysicalMovieItemDto dto = new LitePhysicalMovieItemDto();

        dto.setMovie(movieMapper.convert(pmi.getMovie()));
        dto.setRentalPrice(pmi.getRentalPrice());
        dto.setDiscountedPrice(pmi.getDiscountedPrice());
        dto.setMedium(pmi.getMedium());
        dto.setState(pmi.getState());

        return dto;
    }

    public void transfer(PhysicalMovieItemDto dto, PhysicalMovieItem pmi) {
        if(dto == null)
            throw new MapperTransferException("The physical movie item DTO is NULL");
        if(pmi == null)
            throw new MapperTransferException("The physical movie item Entity is NULL");

        pmi.setMovie(
                movieDao.findById( dto.getMovieID() )
                        .orElseThrow( () -> new IllegalArgumentException("Movie not found"))
        );
        if(dto.getRentalPrice() != null)
            pmi.setRentalPrice(dto.getRentalPrice());
        if(dto.getDiscountedPrice() != null)
            pmi.setDiscountedPrice(dto.getDiscountedPrice());
        if(dto.getMedium() != null)
            pmi.setMedium(dto.getMedium());
        if(dto.getState() != null)
            pmi.setState(dto.getState());
    }

}
