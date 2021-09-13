package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.WeeklyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteWeeklyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.WeeklyRecord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class WeeklyRecordMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;

    public LiteWeeklyRecordDto convert(WeeklyRecord wr) {
        if(wr == null)
            throw new MapperConversionException("The weekly record is NULL");

        LiteWeeklyRecordDto dto = new LiteWeeklyRecordDto();

        dto.setName( wr.getName() );
        dto.setMovie( movieMapper.convert( wr.getMovie() ) );
        dto.setThisWeekPurchases( wr.getThisWeekPurchases() );
        dto.setCurrentTotalPurchases( wr.getCurrentTotalPurchases() );

        return dto;
    }

    public void transfer(WeeklyRecordDto dto, WeeklyRecord wr) {
        if(dto == null)
            throw new MapperTransferException("The weekly record DTO is NULL");
        if(wr == null)
            throw new MapperTransferException("The weekly record Entity is NULL");

        if( dto.getName() != null )
            wr.setName( dto.getName() );
        if(dto.getMovie() != null) {
            if ( movieDao.retrieveMoviesByTitle(dto.getMovie().getTitle()).size() != 0 )
                System.out.println("Movies with similar names do exist in database. Do you want to check them out?");
            else {
                Movie movie = ModelFactory.initMovie();
                movieMapper.transfer(dto.getMovie(), movie);
                movieDao.add(movie);
                wr.setMovie(movie);
            }
        }
        if( dto.getThisWeekPurchases() != null )
            wr.setThisWeekPurchases( dto.getThisWeekPurchases() );
        if( dto.getCurrentTotalPurchases() != null )
            wr.setCurrentTotalPurchases( dto.getCurrentTotalPurchases() );
    }

}
