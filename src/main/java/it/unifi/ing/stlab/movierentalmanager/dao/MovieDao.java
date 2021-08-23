package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;

import javax.ejb.Stateless;
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
        oldMovie.setDirector(m.getDirector());
        oldMovie.setGenre(m.getGenre());
        oldMovie.setRating(m.getRating());
        oldMovie.setCharacters(m.getCharacters());
        oldMovie.setCrew(m.getCrew());
        oldMovie.setCast(m.getCast());
        oldMovie.setItems(m.getItems());
    }

    /*public List<Movie> findMoviesByActor(Actor actor) {
       String q = "SELECT m FROM Movie m" +
               "WHERE (:actor) in m.cast" +
               "ORDER BY m.id DESC";
       List<Movie> movies = getEm().createQuery(q)
                                   .setParameter("actor", actor)
                                   .getResultList();
       return movies;
    }*/

}
