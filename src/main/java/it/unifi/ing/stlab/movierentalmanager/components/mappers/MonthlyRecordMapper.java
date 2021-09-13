package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.MonthlyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteMonthlyRecordDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class MonthlyRecordMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;

    public LiteMonthlyRecordDto convert(MonthlyRecord mr) {
        if(mr == null)
            throw new MapperConversionException("The monthly record is NULL");

        LiteMonthlyRecordDto dto = new LiteMonthlyRecordDto();

        dto.setName( mr.getName() );
        dto.setMovie( movieMapper.convert( mr.getMovie() ) );
        dto.setThisMonthPurchases( mr.getThisMonthPurchases() );
        dto.setCurrentTotalPurchases( mr.getCurrentTotalPurchases() );

        return dto;
    }

    public void transfer(MonthlyRecordDto dto, MonthlyRecord mr) {
        if(dto == null)
            throw new MapperTransferException("The monthly record DTO is NULL");
        if(mr == null)
            throw new MapperTransferException("The monthly record Entity is NULL");

        if( dto.getName() != null )
            mr.setName( dto.getName() );
        if(dto.getMovie() != null) {
            if ( movieDao.retrieveMoviesByTitle(dto.getMovie().getTitle()).size() != 0 )
                System.out.println("Movies with similar names do exist in database. Do you want to check them out?");
            else {
                Movie movie = ModelFactory.initMovie();
                movieMapper.transfer(dto.getMovie(), movie);
                movieDao.add(movie);
                mr.setMovie(movie);
            }
        }
        if( dto.getThisMonthPurchases() != null )
            mr.setThisMonthPurchases( dto.getThisMonthPurchases() );
        if( dto.getCurrentTotalPurchases() != null )
            mr.setCurrentTotalPurchases( dto.getCurrentTotalPurchases() );
    }

}