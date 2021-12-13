package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import java.io.Serializable;

public class LiteYearlyRecordDto {

    private String name;
    private LiteMovieDto movie;
    private Integer thisYearPurchases;
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
