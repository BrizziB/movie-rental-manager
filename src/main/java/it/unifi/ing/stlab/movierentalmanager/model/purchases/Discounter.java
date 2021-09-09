package it.unifi.ing.stlab.movierentalmanager.model.purchases;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public interface Discounter {

    BigDecimal apply(BigDecimal amount);

    default Discounter combine(Discounter after) {
        return value -> after.apply( this.apply(value) );
    }

    // SEASONAL DISCOUNTS
    static Discounter easterDiscounter() {
        return amount -> amount.multiply( BigDecimal.valueOf(0.7) );
    }

    static Discounter summerDiscounter() {
        return amount -> amount.multiply( BigDecimal.valueOf(0.8) );
    }

    static Discounter blackFridayDiscounter() {
        return amount -> amount.multiply( BigDecimal.valueOf(0.6) );
    }

    static Discounter christmasDiscounter() {
        return amount -> amount.multiply( BigDecimal.valueOf(0.8) ); // 20% discount
    }

    static Discounter newYearDiscounter() {
        return amount -> amount.multiply( BigDecimal.valueOf(0.9) );
    }

    // MEMBERSHIP DISCOUNT
    static Discounter premiumDiscounter() {
        return amount -> amount.multiply( BigDecimal.valueOf(0.9) );
    }

    // COUPON DISCOUNT
    static Discounter couponDiscounter(BigDecimal coupon) {
        return amount -> amount.multiply( coupon );
    }

}
