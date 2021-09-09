package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class MovieDao extends BaseDao<Movie> {

   public MovieDao() {
       super(Movie.class);
   }

    @Transactional
    public void update(Movie m) {
        Movie oldMovie = getEm().find(Movie.class, m.getId());
        oldMovie.setTitle(m.getTitle());
        oldMovie.setYear(m.getYear());
        oldMovie.setLength(m.getLength());
        oldMovie.setPlot(m.getPlot());
        oldMovie.setCountry(m.getCountry());
        oldMovie.setLanguage(m.getLanguage());
        oldMovie.setBudget(m.getBudget());
        oldMovie.setCriticRating(m.getCriticRating());
        oldMovie.setDirector(m.getDirector());
        oldMovie.setGenre(m.getGenre());
        oldMovie.setRating(m.getRating());
        oldMovie.setCharacters(m.getCharacters());
        oldMovie.setCrew(m.getCrew());
        oldMovie.setCast(m.getCast());
        oldMovie.setItems(m.getItems());
    }

    public List<Movie> retrieveMoviesByTitle(String title) {
        TypedQuery<Movie> query = getEm().createQuery(
                "FROM Movie m WHERE m.title LIKE CONCAT('%', :title, '%')",
                Movie.class
        ).setParameter("title", title);
        return query.getResultList();
    }

    @Transactional
    public Movie fetchMovieWithCast(Long id) {
       TypedQuery<Movie> query = getEm().createQuery(
               "FROM Movie m JOIN FETCH m.cast WHERE m.id = :id",
               Movie.class
       ).setParameter("id", id);
       return query.getSingleResult();
    }

}
