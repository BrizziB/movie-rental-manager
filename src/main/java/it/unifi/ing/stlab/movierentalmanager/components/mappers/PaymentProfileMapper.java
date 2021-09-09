package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LitePaymentProfileDto;
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

    public void transfer(LitePaymentProfileDto dto, PaymentProfile pp) {
        if(dto == null)
            throw new MapperTransferException("The payment profile DTO is NULL");
        if(pp == null)
            throw new MapperTransferException("The payment profile Entity is NULL");

        if(dto.getCustomer() != null) {
            if ( customerDao.retrieveCustomersByName( dto.getCustomer().getName() ).size() != 0) {
                System.out.println("Customers with similar names do exist in database. Do you want to check them out?");
            }
            else {
                Customer customer = ModelFactory.initCustomer();
                customerMapper.transfer(dto.getCustomer(), customer);
                customerDao.add(customer);
                pp.setCustomer(customer);
            }
        }
        if(dto.getCreditCardType() != null)
            pp.setCreditCardType(dto.getCreditCardType());
        if(dto.getCreditCardNumber() != null)
            pp.setCreditCardNumber(dto.getCreditCardNumber());
    }

}
