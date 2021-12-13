package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;

public class YearlyRecordDto {

    private String name;
    private MovieDto movie;
    private Integer thisYearPurchases;
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
