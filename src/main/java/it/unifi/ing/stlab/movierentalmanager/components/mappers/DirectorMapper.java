package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteDirectorDto;
import it.unifi.ing.stlab.movierentalmanager.dao.DirectorDao;
import it.unifi.ing.stlab.movierentalmanager.model.Director;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class DirectorMapper {

    @Inject private DirectorDao dao;

    public LiteDirectorDto convert(Director d) {
        if(d == null)
            throw new MapperConversionException("The director is NULL");

        LiteDirectorDto dto = new LiteDirectorDto();

        dto.setName(d.getName());
        dto.setSurname(d.getSurname());
        dto.setBirthDate(d.getBirthDate());
        dto.setCountry(d.getCountry());

        return dto;
    }

    public void transfer(LiteDirectorDto dto, Director d) {
        if(dto == null)
            throw new MapperTransferException("The director DTO is NULL");
        if(d == null)
            throw new MapperTransferException("The director Entity is NULL");

        if(dto.getName() != null)
            d.setName(dto.getName());
        if(dto.getSurname() != null)
            d.setSurname(dto.getSurname());
        if(dto.getBirthDate() != null)
            d.setBirthDate(dto.getBirthDate());
        if(dto.getCountry() != null)
            d.setCountry(dto.getCountry());
    }

}
