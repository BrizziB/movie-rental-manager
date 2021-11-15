package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.PaymentProfileDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LitePaymentProfileDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.CustomerDao;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.PaymentProfile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PaymentProfileMapper {

    @Inject private CustomerMapper customerMapper;
    @Inject private CustomerDao customerDao;

    public LitePaymentProfileDto convert(PaymentProfile pp) {
        if(pp == null)
            throw new MapperConversionException("The payment profile is NULL");

        LitePaymentProfileDto dto = new LitePaymentProfileDto();

        dto.setCustomer(customerMapper.convert(pp.getCustomer()));
        dto.setCreditCardType(pp.getCreditCardType());
        dto.setCreditCardNumber(pp.getCreditCardNumber());

        return dto;
    }

    public void transfer(PaymentProfileDto dto, PaymentProfile pp) {
        if(dto == null)
            throw new MapperTransferException("The payment profile DTO is NULL");
        if(pp == null)
            throw new MapperTransferException("The payment profile Entity is NULL");

        pp.setCustomer(
                customerDao.findById( dto.getCustomerID() )
                           .orElseThrow( () -> new IllegalArgumentException("Customer not found"))
        );
        if(dto.getCreditCardType() != null)
            pp.setCreditCardType(dto.getCreditCardType());
        if(dto.getCreditCardNumber() != null)
            pp.setCreditCardNumber(dto.getCreditCardNumber());
    }

}
