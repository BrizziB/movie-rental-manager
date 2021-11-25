package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.EmployeeDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteEmployeeDto;
import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EmployeeMapper {

    public LiteEmployeeDto convert(Employee e) {
        if(e == null)
            throw new MapperConversionException("The employee is NULL");

        LiteEmployeeDto dto = new LiteEmployeeDto();

        dto.setName(e.getName());
        dto.setAddress(e.getAddress());
        dto.setPhoneNumber(e.getPhoneNumber());
        dto.setWebUser(e.getWebUser());
        dto.setRole(e.getRole());

        return dto;
    }

    public void transfer(EmployeeDto dto, Employee e) {
        if(dto == null)
            throw new MapperTransferException("The employee DTO is NULL");
        if(e == null)
            throw new MapperTransferException("The employee Entity is NULL");

        if(dto.getName() != null)
            e.setName(dto.getName());
        if(dto.getAddress() != null)
            e.setAddress(dto.getAddress());
        if(dto.getPhoneNumber() != null)
            e.setPhoneNumber(dto.getPhoneNumber());
        if(dto.getWebUser() != null) {
            WebUser hashedPasswordWebUser = dto.getWebUser();
            hashedPasswordWebUser.setPassword(dto.getWebUser().getPassword() );
            e.setWebUser(hashedPasswordWebUser);
        }
        if(dto.getRole() != null)
            e.setRole(dto.getRole());
    }

}
