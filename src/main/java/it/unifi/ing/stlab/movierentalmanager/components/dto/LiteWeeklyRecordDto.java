package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;

public class LiteWeeklyRecordDto implements Serializable {

    private String name;
    private LiteMovieDto movie;
    private Integer thisWeekPurchases;
    private Long currentTotalPurchases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LiteMovieDto getMovie() {
        return movie;
    }

    public void setMovie(LiteMovieDto movie) {
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
