package it.unifi.ing.stlab.movierentalmanager.model.purchases;

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
