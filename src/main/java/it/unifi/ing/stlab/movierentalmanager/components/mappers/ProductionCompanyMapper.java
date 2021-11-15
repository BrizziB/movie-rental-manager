package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.MovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.ProductionCompanyDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteProductionCompanyDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.movies.ProductionCompany;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ProductionCompanyMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;

    public LiteProductionCompanyDto convert(ProductionCompany pc) {
        if(pc == null)
            throw new MapperConversionException("The production company is NULL");

        LiteProductionCompanyDto dto = new LiteProductionCompanyDto();

        dto.setName(pc.getName());
        dto.setCountry(pc.getCountry());
        dto.setFoundationDate(pc.getFoundationDate());
        dto.setWebSiteURL(pc.getWebSiteURL());
//        serializeMovies(dto, pc.getMovies());

        return dto;
    }

    public void transfer(ProductionCompanyDto dto, ProductionCompany pc) {
        if(dto == null)
            throw new MapperTransferException("The production company DTO is NULL");
        if(pc == null)
            throw new MapperTransferException("The production company Entity is NULL");

        if(dto.getName() != null)
            pc.setName(dto.getName());
        if(dto.getCountry() != null)
            pc.setCountry(dto.getCountry());
        if(dto.getFoundationDate() != null)
            pc.setFoundationDate(dto.getFoundationDate());
        if(dto.getWebSiteURL() != null)
            pc.setWebSiteURL(dto.getWebSiteURL());
        if(dto.getMoviesIDs() != null)
            deSerializeMovies(pc, dto.getMoviesIDs());
    }

//    private void serializeMovies(LiteProductionCompanyDto dto, List<Movie> movies) {
//        if(movies != null && movies.size() > 0)
//            for (Movie movie : movies)
//                dto.getMovies().add( movieMapper.convert(movie) );
//    }

    private void deSerializeMovies(ProductionCompany pc, List<Long> moviesIDs) {
        pc.getMovies().clear();

        if(moviesIDs != null || moviesIDs.size() > 0) {
            for (Long ID : moviesIDs) {
                pc.getMovies().add(
                        movieDao.findById(ID)
                                .orElseThrow(() -> new IllegalArgumentException("Movie not found"))
                );
            }
        }
    }

}
