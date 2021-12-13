package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import it.unifi.ing.stlab.movierentalmanager.model.movies.Genre;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Rating;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class LiteMovieDto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LiteMovieDto)) return false;
        LiteMovieDto that = (LiteMovieDto) o;
        return disabled == that.disabled &&
                Objects.equals(title, that.title) &&
                Objects.equals(year, that.year) &&
                Objects.equals(length, that.length) &&
                Objects.equals(plot, that.plot) &&
                Objects.equals(country, that.country) &&
                Objects.equals(language, that.language) &&
                Objects.equals(budget, that.budget) &&
                Objects.equals(criticRating, that.criticRating) &&
                genre == that.genre &&
                rating == that.rating &&
                Objects.equals(director, that.director);
    }

}
