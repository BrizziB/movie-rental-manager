package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Entity
@DiscriminatorValue("physical")
public class PhysicalMovieItem extends MovieItem {

    @Enumerated(EnumType.STRING) private MovieMedium medium;
    @Enumerated(EnumType.STRING) private ItemState state;

    public PhysicalMovieItem() {
        super();
    }

    public PhysicalMovieItem(UUID uuid) {
        super(uuid);
    }

    public MovieMedium getMedium() {
        return medium;
    }

    public void setMedium(MovieMedium medium) {
        this.medium = medium;
    }

    public ItemState getState() {
        return state;
    }

    public void setState(ItemState state) {
        this.state = state;
    }

}
