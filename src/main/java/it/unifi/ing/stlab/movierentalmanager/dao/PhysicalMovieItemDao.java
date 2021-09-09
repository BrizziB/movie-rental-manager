package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class PhysicalMovieItemDao extends BaseDao<PhysicalMovieItem> {

    public PhysicalMovieItemDao() {
        super(PhysicalMovieItem.class);
    }

    @Transactional
    public void update(PhysicalMovieItem pmi) {
        PhysicalMovieItem oldPMI = getEm().find(PhysicalMovieItem.class, pmi.getId());
        oldPMI.setMovie(pmi.getMovie());
        oldPMI.setRentalPrice(pmi.getRentalPrice());
        oldPMI.setDiscountedPrice(pmi.getDiscountedPrice());
        oldPMI.setMedium(pmi.getMedium());
        oldPMI.setState(pmi.getState());
    }

    public List<PhysicalMovieItem> retrievePhysicalMovieItemsByMovieTitle(String title) {
        TypedQuery<PhysicalMovieItem> query = getEm().createQuery(
                "FROM PhysicalMovieItem pmi WHERE pmi.movie.title LIKE CONCAT('%', :title, '%')",
                PhysicalMovieItem.class
        ).setParameter("title", title);
        return query.getResultList();
    }

    public List<PhysicalMovieItem> retrievePhysicalMovieItemsByMovieId(Long id) {
        TypedQuery<PhysicalMovieItem> query = getEm().createQuery(
                "FROM PhysicalMovieItem pmi WHERE pmi.movie.id = :id",
                PhysicalMovieItem.class
        ).setParameter("id", id);
        return query.getResultList();
    }

}
