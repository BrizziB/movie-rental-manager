package it.unifi.ing.stlab.movierentalmanager.model;

import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
public class WeekChart extends BaseEntity {

    @ManyToOne private Movie movie;
    @Temporal(TemporalType.DATE) private Date date;
    private Integer currentTotalPurchases;
    private Integer lastTotalPurchases;
    private Integer delta;

    public WeekChart() {
        super();
    }

    public WeekChart(UUID uuid) {
        super(uuid);
        currentTotalPurchases = movie.getTotalPurchases();
        lastTotalPurchases = null;
        delta = currentTotalPurchases - lastTotalPurchases;
    }

}
