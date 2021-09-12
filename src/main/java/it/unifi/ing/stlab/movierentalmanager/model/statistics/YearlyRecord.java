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
@Table(name = "yearly_records")
public class YearlyRecord extends BaseEntity {

    private String name;
    @OneToOne @JoinColumn(name = "movie_id") private Movie movie;
    private Integer thisYearPurchases;
    private Long currentTotalPurchases;

    public YearlyRecord() {
        super();
    }

    public YearlyRecord(Movie movie, Integer thisYearPurchases, Long currentTotalPurchases) {
        super();
        this.name = "Yearly record: " + new SimpleDateFormat("yyyy").format(getCreationTime());
        this.movie = movie;
        this.thisYearPurchases = thisYearPurchases;
        this.currentTotalPurchases = currentTotalPurchases;
    }

    public YearlyRecord(UUID uuid, Movie movie, Integer thisYearPurchases, Long currentTotalPurchases) {
        super(uuid);
        this.name = "Yearly record: " + new SimpleDateFormat("yyyy").format(getCreationTime());
        this.movie = movie;
        this.thisYearPurchases = thisYearPurchases;
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

    public Integer getThisYearPurchases() {
        return thisYearPurchases;
    }

    public void setThisYearPurchases(Integer thisYearPurchases) {
        this.thisYearPurchases = thisYearPurchases;
    }

    public Long getCurrentTotalPurchases() {
        return currentTotalPurchases;
    }

    public void setCurrentTotalPurchases(Long currentTotalPurchases) {
        this.currentTotalPurchases = currentTotalPurchases;
    }
}
