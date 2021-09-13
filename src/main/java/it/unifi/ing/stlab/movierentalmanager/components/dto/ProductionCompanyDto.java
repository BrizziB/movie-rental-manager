package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProductionCompanyDto implements Serializable {

    private String name;
    private String country;
    private Date foundationDate;
    private String webSiteURL;
    private List<MovieDto> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getWebSiteURL() {
        return webSiteURL;
    }

    public void setWebSiteURL(String webSiteURL) {
        this.webSiteURL = webSiteURL;
    }

    public List<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDto> movies) {
        this.movies = movies;
    }
}
