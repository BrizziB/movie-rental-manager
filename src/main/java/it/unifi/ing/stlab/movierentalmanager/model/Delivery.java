package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Delivery {

    private String address;
    private String type;
    private String courier;
    private BigDecimal cost;

    public Delivery() {

    }

}
