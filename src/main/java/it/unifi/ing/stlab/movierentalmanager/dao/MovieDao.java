package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class MovieDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addMovie(Movie m) {
        em.persist(m);
    }

    public Optional<Movie> findMovieById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    public List<Movie> findAllMovies() {
        TypedQuery<Movie> query = em.createQuery("FROM Movie", Movie.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    @Transactional
    public void updateMovie(Movie m) {
        Movie oldMovie = em.find(Movie.class, m.getId());
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

    @Transactional
    public void saveMovie(Movie m) {
        if(m.getId() != null)
            em.merge(m);
        else
            em.persist(m);
    }

    @Transactional
    public int deleteMovieById(Long id) {
        Query query = em.createQuery("DELETE FROM Movie m WHERE m.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
