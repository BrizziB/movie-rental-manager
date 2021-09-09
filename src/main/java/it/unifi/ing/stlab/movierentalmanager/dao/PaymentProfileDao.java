package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.purchases.PaymentProfile;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class PaymentProfileDao extends BaseDao<PaymentProfile> {

    public PaymentProfileDao() {
        super(PaymentProfile.class);
    }

    @Transactional
    public void update(PaymentProfile pp) {
        PaymentProfile oldPaymentProfile = getEm().find(PaymentProfile.class, pp.getId());
        oldPaymentProfile.setCustomer(pp.getCustomer());
        oldPaymentProfile.setCreditCardType(pp.getCreditCardType());
        oldPaymentProfile.setCreditCardNumber(pp.getCreditCardNumber());
    }

}
