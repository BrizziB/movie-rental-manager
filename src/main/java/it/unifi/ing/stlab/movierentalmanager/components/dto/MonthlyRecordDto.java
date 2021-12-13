package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;

public class MonthlyRecordDto {

    private String name;
    private MovieDto movie;
    private Integer thisMonthPurchases;
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
