package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCustomerDto;
import it.unifi.ing.stlab.movierentalmanager.model.Customer;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CustomerMapper {

    public LiteCustomerDto convert(Customer c) {
        if(c == null)
            throw new MapperConversionException("The customer is NULL");

        LiteCustomerDto dto = new LiteCustomerDto();

        dto.setName(c.getName());
        dto.setAddress(c.getAddress());
        dto.setPhoneNumber(c.getPhoneNumber());
        dto.setWebUser(c.getWebUser());
        dto.setMembership(c.getMembership());

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
    }
}
