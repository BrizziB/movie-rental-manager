package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Embeddable
public class Payment {

    @Transient private BigDecimal total;
    // TODO temporal
    private Timestamp timestamp;
    @Enumerated private PaymentStatus paymentStatus;
    private String details;

    public Payment() {
        super();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
