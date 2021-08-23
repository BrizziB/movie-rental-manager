package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.CrewMember;
import it.unifi.ing.stlab.movierentalmanager.model.Genre;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.Rating;

import java.io.Serializable;

public class LiteMovieDto implements Serializable {

    private String title;
    private String year;
    private Integer length;
    private String plot;
    private String country;
    private String language;
    private Integer budget;
    private Double criticRating;
    private LiteCrewMemberDto director;
    private Genre genre;
    private Rating rating;

    public static LiteMovieDto mapMovieToLiteMovie(Movie movie) {
        LiteMovieDto liteMovieDto = new LiteMovieDto();

        liteMovieDto.title = movie.getTitle();
        liteMovieDto.year = movie.getYear();
        liteMovieDto.length = movie.getLength();
        liteMovieDto.plot = movie.getPlot();
        liteMovieDto.country = movie.getCountry();
        liteMovieDto.language = movie.getLanguage();
        liteMovieDto.budget = movie.getBudget();
        liteMovieDto.criticRating = movie.getCriticRating();
        liteMovieDto.director = LiteCrewMemberDto.mapCrewMemberToLiteCrewMember(movie.getDirector());
        liteMovieDto.genre = movie.getGenre();
        liteMovieDto.rating = movie.getRating();

        return liteMovieDto;
    }

}
