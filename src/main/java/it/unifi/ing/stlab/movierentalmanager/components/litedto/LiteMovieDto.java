package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import it.unifi.ing.stlab.movierentalmanager.model.movies.Genre;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Rating;

import java.io.Serializable;
import java.util.List;

public class LiteMovieDto implements Serializable {

    private String title;
    private String year;
    private Integer length;
    private String plot;
    private String country;
    private String language;
    private Integer budget;
    private Double criticRating;
    private boolean disabled;
    private Genre genre;
    private Rating rating;
    private LiteDirectorDto director;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Double getCriticRating() {
        return criticRating;
    }

    public void setCriticRating(Double criticRating) {
        this.criticRating = criticRating;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public LiteDirectorDto getDirector() {
        return director;
    }

    public void setDirector(LiteDirectorDto director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

}
