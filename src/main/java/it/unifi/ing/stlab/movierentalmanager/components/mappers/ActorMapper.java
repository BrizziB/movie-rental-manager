package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteActorDto;
import it.unifi.ing.stlab.movierentalmanager.model.Actor;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ActorMapper {

    public LiteActorDto convert(Actor a) {
        if(a == null)
            throw new MapperConversionException("The actor is NULL");

        LiteActorDto dto = new LiteActorDto();

        dto.setName(a.getName());
        dto.setSurname(a.getSurname());
        dto.setBirthDate(a.getBirthDate());
        dto.setCountry(a.getCountry());
        dto.setStageName(a.getStageName());

        return dto;
    }

    public void transfer(LiteActorDto dto, Actor a) {
        if(dto == null)
            throw new MapperTransferException("The actor DTO is NULL");
        if(a == null)
            throw new MapperTransferException("The actor Entity is NULL");

        if(dto.getName() != null)
            a.setName(dto.getName());
        if(dto.getSurname() != null)
            a.setSurname(dto.getSurname());
        if(dto.getBirthDate() != null)
            a.setBirthDate(dto.getBirthDate());
        if(dto.getCountry() != null)
            a.setCountry(dto.getCountry());
        if(dto.getStageName() != null)
            a.setStageName(dto.getStageName());
    }

}
