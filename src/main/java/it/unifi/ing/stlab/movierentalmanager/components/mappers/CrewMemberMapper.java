package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCrewMemberDto;
import it.unifi.ing.stlab.movierentalmanager.dao.CrewMemberDao;
import it.unifi.ing.stlab.movierentalmanager.model.CrewMember;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CrewMemberMapper {

    @Inject private CrewMemberDao dao;

    public LiteCrewMemberDto convert(CrewMember cm) {
        if(cm == null)
            throw new MapperConversionException("The crew member is NULL");

        LiteCrewMemberDto dto = new LiteCrewMemberDto();

        dto.setName(cm.getName());
        dto.setSurname(cm.getSurname());
        dto.setBirthDate(cm.getBirthDate());
        dto.setCountry(cm.getCountry());
        dto.setRole(cm.getRole());

        return dto;
    }

    public void transfer(LiteCrewMemberDto dto, CrewMember cm) {
        if(dto == null)
            throw new MapperTransferException("The crew member DTO is NULL");
        if(cm == null)
            throw new MapperTransferException("The crew member Entity is NULL");

        if(dto.getName() != null)
            cm.setName(dto.getName());
        if(dto.getSurname() != null)
            cm.setSurname(dto.getSurname());
        if(dto.getBirthDate() != null)
            cm.setBirthDate(dto.getBirthDate());
        if(dto.getCountry() != null)
            cm.setCountry(dto.getCountry());
        if(dto.getRole() != null)
            cm.setRole(dto.getRole());
    }

}
