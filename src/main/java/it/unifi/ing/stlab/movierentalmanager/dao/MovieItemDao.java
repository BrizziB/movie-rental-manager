package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.items.MovieItem;

import javax.transaction.Transactional;

public class MovieItemDao extends BaseDao<MovieItem> {

    public MovieItemDao() {
        super(MovieItem.class);
    }

    @Transactional
    public void update(MovieItem mi) {
        MovieItem oldMovieItem = getEm().find(MovieItem.class, mi.getId());
        oldMovieItem.setMovie(mi.getMovie());
        oldMovieItem.setRentalPrice(mi.getRentalPrice());
        oldMovieItem.setDiscountedPrice(mi.getDiscountedPrice());
    }

}
