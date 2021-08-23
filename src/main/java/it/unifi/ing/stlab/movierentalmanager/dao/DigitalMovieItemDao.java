package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.DigitalMovieItem;

import javax.transaction.Transactional;

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

}
