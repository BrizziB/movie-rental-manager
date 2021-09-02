package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {

    private String title;
    private String year;
    private Integer length;
    private String plot;
    private String country;
    private String language;
    private Integer budget;
    private Double criticRating;
    private boolean disabled;
    @Enumerated(EnumType.STRING) private Genre genre;
    @Enumerated(EnumType.STRING) private Rating rating;

    @ManyToOne private Director director;

    @ManyToMany
    @JoinTable(name="movies_characters",
            joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="character_id"))
    private List<Character> characters;

    @ManyToMany
    @JoinTable(name="movies_crews",
            joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="crew_member_id"))
    private List<CrewMember> crew;

    @ManyToMany
    @JoinTable(name="movies_actors",
            joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="actor_id"))
    private List<Actor> cast;

    @ManyToMany
    @JoinTable(name="movies_producers",
            joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="producer_id"))
    private List<ProductionCompany> producers;

    @OneToMany(mappedBy = "movie")
    private List<MovieItem> items;

    public Movie() {
        super();
        this.cast = new ArrayList<Actor>();
        this.crew = new ArrayList<CrewMember>();
        this.characters = new ArrayList<Character>();
        this.items = new ArrayList<MovieItem>();
        disabled = false;
    }

    public Movie(UUID uuid) {
        super(uuid);
        this.cast = new ArrayList<Actor>();
        this.crew = new ArrayList<CrewMember>();
        this.characters = new ArrayList<Character>();
        this.items = new ArrayList<MovieItem>();
        disabled = false;
    }

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

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
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

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public List<ProductionCompany> getProducers() {
        return producers;
    }

    public void setProducers(List<ProductionCompany> producers) {
        this.producers = producers;
    }

    public List<MovieItem> getItems() {
        return items;
    }

    public void setItems(List<MovieItem> items) {
        this.items = items;
    }
}
