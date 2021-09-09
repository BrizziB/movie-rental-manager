package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.items.DigitalMovieItem;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class DigitalMovieItemDao extends BaseDao<DigitalMovieItem> {

    public DigitalMovieItemDao() {
        super(DigitalMovieItem.class);
    }

    @Transactional
    public void update(DigitalMovieItem dmi) {
        DigitalMovieItem oldDMI = getEm().find(DigitalMovieItem.class, dmi.getId());
        oldDMI.setMovie(dmi.getMovie());
        oldDMI.setRentalPrice(dmi.getRentalPrice());
        oldDMI.setDiscountedPrice(dmi.getDiscountedPrice());
        oldDMI.setUrl(dmi.getUrl());
        oldDMI.setExpirationDate(dmi.getExpirationDate());
    }

    public List<DigitalMovieItem> retrieveDigitalMovieItemsByMovieTitle(String title) {
        TypedQuery<DigitalMovieItem> query = getEm().createQuery(
                "FROM DigitalMovieItem dmi WHERE dmi.movie.title LIKE CONCAT('%', :title, '%')",
                DigitalMovieItem.class
        ).setParameter("title", title);
        return query.getResultList();
    }

    public List<DigitalMovieItem> retrieveDigitalMovieItemsByMovieId(Long id) {
        TypedQuery<DigitalMovieItem> query = getEm().createQuery(
                "FROM DigitalMovieItem dmi WHERE dmi.movie.id = :id",
                DigitalMovieItem.class
        ).setParameter("id", id);
        return query.getResultList();
    }

}
