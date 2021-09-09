package it.unifi.ing.stlab.movierentalmanager.model.movies;

import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "production_companies")
public class ProductionCompany extends BaseEntity {

    private String name;
    private String country;
    @Temporal(TemporalType.DATE) private Date foundationDate;
    private String webSiteURL;

    @ManyToMany(mappedBy = "producers")
    private List<Movie> movies;

    public ProductionCompany() {
        super();
        movies = new ArrayList<Movie>();
    }

    public ProductionCompany(UUID uuid) {
        super(uuid);
        movies = new ArrayList<Movie>();
    }

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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
