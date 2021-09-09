package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCustomerDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LitePaymentProfileDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.PaymentProfileDao;
import it.unifi.ing.stlab.movierentalmanager.model.users.Customer;
import it.unifi.ing.stlab.movierentalmanager.model.purchases.PaymentProfile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class CustomerMapper {

    @Inject private PaymentProfileMapper paymentProfileMapper;
    @Inject private PaymentProfileDao paymentProfileDao;

    public LiteCustomerDto convert(Customer c) {
        if(c == null)
            throw new MapperConversionException("The customer is NULL");

        LiteCustomerDto dto = new LiteCustomerDto();

        dto.setName(c.getName());
        dto.setAddress(c.getAddress());
        dto.setPhoneNumber(c.getPhoneNumber());
        dto.setWebUser(c.getWebUser());
        dto.setMembership(c.getMembership());
        dto.setCustomerDetails(c.getCustomerDetails());
        serializePaymentProfiles(dto, c.getPaymentProfiles());

        return dto;
    }

    public void transfer(LiteCustomerDto dto, Customer c) {
        if(dto == null)
            throw new MapperTransferException("The customer DTO is NULL");
        if(c == null)
            throw new MapperTransferException("The customer Entity is NULL");

        if(dto.getName() != null)
            c.setName(dto.getName());
        if(dto.getAddress() != null)
            c.setAddress(dto.getAddress());
        if(dto.getPhoneNumber() != null)
            c.setPhoneNumber(dto.getPhoneNumber());
        if(dto.getWebUser() != null)
            c.setWebUser(dto.getWebUser());
        if(dto.getMembership() != null)
            c.setMembership(dto.getMembership());
        if(dto.getCustomerDetails() != null)
            c.setCustomerDetails(dto.getCustomerDetails());
        if(dto.getPaymentProfiles() != null)
            deSerializePaymentProfiles(c, dto.getPaymentProfiles());
    }

    private void serializePaymentProfiles(LiteCustomerDto dto, List<PaymentProfile> paymentProfiles) {
        if(paymentProfiles != null && paymentProfiles.size() > 0)
            for (PaymentProfile pp : paymentProfiles)
                dto.getPaymentProfiles().add(paymentProfileMapper.convert(pp) );
    }

    private void deSerializePaymentProfiles(Customer c, List<LitePaymentProfileDto> litePaymentProfiles) {
        c.getPaymentProfiles().clear();

        if(litePaymentProfiles != null && litePaymentProfiles.size() > 0)
            for (LitePaymentProfileDto litePaymentProfile : litePaymentProfiles) {
                if ( c.getPaymentProfiles().size() != 0)
                    System.out.println("This customer has already at least one payment profile, do you want to check them out?");
                else {
                    PaymentProfile pp = ModelFactory.initPaymentProfile();
                    paymentProfileMapper.transfer(litePaymentProfile, pp);
                    paymentProfileDao.add(pp);
                    c.getPaymentProfiles().add(pp);
                }
            }
    }

}
