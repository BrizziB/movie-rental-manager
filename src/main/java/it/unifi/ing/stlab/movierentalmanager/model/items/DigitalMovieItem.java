package it.unifi.ing.stlab.movierentalmanager.model.items;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
@DiscriminatorValue("digital")
public class DigitalMovieItem extends MovieItem {

    private String url;
    @Temporal(TemporalType.DATE) private Date expirationDate;

    public DigitalMovieItem() {
        super();
    }

    public DigitalMovieItem(UUID uuid) {
        super(uuid);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
