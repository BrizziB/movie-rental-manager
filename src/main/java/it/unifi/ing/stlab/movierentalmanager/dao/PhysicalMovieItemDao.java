package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.PhysicalMovieItem;

import javax.transaction.Transactional;

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

}
