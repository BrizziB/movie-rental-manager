package it.unifi.ing.stlab.movierentalmanager.model.statistics;

import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Entity
@Table(name = "monthly_records")
public class MonthlyRecord extends BaseEntity {

    private String name;
    @OneToOne @JoinColumn(name = "movie_id") private Movie movie;
    private Integer thisMonthPurchases;
    private Long currentTotalPurchases;

    public MonthlyRecord() {
        super();
    }

    public MonthlyRecord(Movie movie, Integer thisMonthPurchases, Long currentTotalPurchases) {
        super();
        this.name = "Monthly record: " +
                new SimpleDateFormat("MMM").format(getCreationTime()) + " " +
                new SimpleDateFormat("yyyy").format(getCreationTime());
        this.movie = movie;
        this.thisMonthPurchases = thisMonthPurchases;
        this.currentTotalPurchases = currentTotalPurchases;
    }

    public MonthlyRecord(UUID uuid, Movie movie, Integer thisMonthPurchases, Long currentTotalPurchases) {
        super(uuid);
        this.name = "Monthly record: " +
                new SimpleDateFormat("MMM").format(getCreationTime()) + " " +
                new SimpleDateFormat("yyyy").format(getCreationTime());
        this.movie = movie;
        this.thisMonthPurchases = thisMonthPurchases;
        this.currentTotalPurchases = currentTotalPurchases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getThisMonthPurchases() {
        return thisMonthPurchases;
    }

    public void setThisMonthPurchases(Integer thisMonthPurchases) {
        this.thisMonthPurchases = thisMonthPurchases;
    }

    public Long getCurrentTotalPurchases() {
        return currentTotalPurchases;
    }

    public void setCurrentTotalPurchases(Long currentTotalPurchases) {
        this.currentTotalPurchases = currentTotalPurchases;
    }
}
