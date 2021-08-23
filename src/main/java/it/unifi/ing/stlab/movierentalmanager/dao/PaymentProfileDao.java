package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.PaymentProfile;

import javax.transaction.Transactional;

public class PaymentProfileDao extends BaseDao<PaymentProfile> {

    public PaymentProfileDao() {
        super(PaymentProfile.class);
    }

    @Transactional
    public void update(PaymentProfile pp) {
        PaymentProfile oldPaymentProfile = getEm().find(PaymentProfile.class, pp.getId());
        oldPaymentProfile.setCustomer(pp.getCustomer());
        oldPaymentProfile.setCreditCartType(pp.getCreditCartType());
        oldPaymentProfile.setCreditCartNumber(pp.getCreditCartNumber());
    }

}
