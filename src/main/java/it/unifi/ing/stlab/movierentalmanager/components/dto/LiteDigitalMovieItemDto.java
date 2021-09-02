package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LiteDigitalMovieItemDto implements Serializable {

    private LiteMovieDto movie;
    private BigDecimal rentalPrice;
    private BigDecimal discountedPrice;
    private String url;
    private Date expirationDate;

    public LiteMovieDto getMovie() {
        return movie;
    }

    public void setMovie(LiteMovieDto movie) {
        this.movie = movie;
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
