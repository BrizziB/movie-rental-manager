package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.MovieItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class MovieItemDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addMovieItem(MovieItem mi) {
        em.persist(mi);
    }

    public Optional<MovieItem> findMovieItemById(Long id) {
        return Optional.ofNullable(em.find(MovieItem.class, id));
    }

    public List<MovieItem> findAllMovieItems() {
        TypedQuery<MovieItem> query = em.createQuery("FROM MovieItem", MovieItem.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    @Transactional
    public void updateMovieItem(MovieItem mi) {
        MovieItem oldMovieItem = em.find(MovieItem.class, mi.getId());
        oldMovieItem.setMovie(mi.getMovie());
        oldMovieItem.setRentalPrice(mi.getRentalPrice());
        oldMovieItem.setDiscountedPrice(mi.getDiscountedPrice());
    }

    @Transactional
    public void saveMovieItem(MovieItem mi) {
        if(mi.getId() != null)
            em.merge(mi);
        else
            em.persist(mi);
    }

    @Transactional
    public int deleteMovieItemById(Long id) {
        Query query = em.createQuery("DELETE FROM MovieItem mi WHERE mi.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
