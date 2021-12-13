package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;

public class WeeklyRecordDto {

    private String name;
    private MovieDto movie;
    private Integer thisWeekPurchases;
    private Long currentTotalPurchases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
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
