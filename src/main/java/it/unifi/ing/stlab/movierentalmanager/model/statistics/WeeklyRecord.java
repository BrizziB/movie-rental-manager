package it.unifi.ing.stlab.movierentalmanager.model.statistics;

import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Entity
@Table(name = "weekly_records")
public class WeeklyRecord extends BaseEntity {

    private String name;
    @OneToOne @JoinColumn(name = "movie_id") private Movie movie;
    private Integer thisWeekPurchases;
    private Long currentTotalPurchases;

    public WeeklyRecord() {
        super();
    }

    public WeeklyRecord(Movie movie, Integer thisWeekPurchases, Long currentTotalPurchases) {
        super();
        this.movie = movie;
        this.name = "Weekly record: " +
                new SimpleDateFormat("d").format(getCreationTime()) + " " +
                new SimpleDateFormat("MMM").format(getCreationTime()) + " " +
                new SimpleDateFormat("yyyy").format(getCreationTime());
        this.thisWeekPurchases = thisWeekPurchases;
        this.currentTotalPurchases = currentTotalPurchases;
    }

    public WeeklyRecord(UUID uuid, Movie movie, Integer thisWeekPurchases, Long currentTotalPurchases) {
        super(uuid);
        this.movie = movie;
        this.name = "Weekly record: " +
                new SimpleDateFormat("d").format(getCreationTime()) + " " +
                new SimpleDateFormat("MMM").format(getCreationTime()) + " " +
                new SimpleDateFormat("yyyy").format(getCreationTime());
        this.thisWeekPurchases = thisWeekPurchases;
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

    public Integer getThisWeekPurchases() {
        return thisWeekPurchases;
    }

    public void setThisWeekPurchases(Integer thisWeekPurchases) {
        this.thisWeekPurchases = thisWeekPurchases;
    }

    public Long getCurrentTotalPurchases() {
        return currentTotalPurchases;
    }

    public void setCurrentTotalPurchases(Long currentTotalPurchases) {
        this.currentTotalPurchases = currentTotalPurchases;
    }
}
