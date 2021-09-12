package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteYearlyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.YearlyRecord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class YearlyRecordMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;

    public LiteYearlyRecordDto convert(YearlyRecord yr) {
        if(yr == null)
            throw new MapperConversionException("The yearly record is NULL");

        LiteYearlyRecordDto dto = new LiteYearlyRecordDto();

        dto.setName( yr.getName() );
        dto.setMovie( movieMapper.convert( yr.getMovie() ) );
        dto.setThisYearPurchases( yr.getThisYearPurchases() );
        dto.setCurrentTotalPurchases( yr.getCurrentTotalPurchases() );

        return dto;
    }

    public void transfer(LiteYearlyRecordDto dto, YearlyRecord yr) {
        if(dto == null)
            throw new MapperTransferException("The yearly record DTO is NULL");
        if(yr == null)
            throw new MapperTransferException("The yearly record Entity is NULL");

        if( dto.getName() != null )
            yr.setName( dto.getName() );
        if(dto.getMovie() != null) {
            if ( movieDao.retrieveMoviesByTitle(dto.getMovie().getTitle()).size() != 0 )
                System.out.println("Movies with similar names do exist in database. Do you want to check them out?");
            else {
                Movie movie = ModelFactory.initMovie();
                movieMapper.transfer(dto.getMovie(), movie);
                movieDao.add(movie);
                yr.setMovie(movie);
            }
        }
        if( dto.getThisYearPurchases() != null )
            yr.setThisYearPurchases( dto.getThisYearPurchases() );
        if( dto.getCurrentTotalPurchases() != null )
            yr.setCurrentTotalPurchases( dto.getCurrentTotalPurchases() );
    }

}
