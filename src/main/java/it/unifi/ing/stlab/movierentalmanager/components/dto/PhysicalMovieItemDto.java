package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.items.ItemState;
import it.unifi.ing.stlab.movierentalmanager.model.items.MovieMedium;

import java.io.Serializable;
import java.math.BigDecimal;

public class PhysicalMovieItemDto {

    private Long movieID;
    private BigDecimal rentalPrice;
    private BigDecimal discountedPrice;
    private MovieMedium medium;
    private ItemState state;

    public Long getMovieID() {
        return movieID;
    }

    public void setMovieID(Long movieID) {
        this.movieID = movieID;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
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
