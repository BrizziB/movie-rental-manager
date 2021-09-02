package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCharacterDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.CharacterDao;
import it.unifi.ing.stlab.movierentalmanager.model.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.Character;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CharacterMapper {

    @Inject private CharacterDao dao;
    @Inject private ActorMapper actorMapper;

    public LiteCharacterDto convert(Character c) {
        if (c == null) {
            throw new MapperConversionException("The character is NULL");
        }

        LiteCharacterDto dto = new LiteCharacterDto();

        dto.setName(c.getName());
        dto.setActor(actorMapper.convert(c.getActor()));

        return dto;
    }

    public void transfer(LiteCharacterDto dto, Character c) {
        if(dto == null)
            throw new MapperTransferException("The character DTO is NULL");
        if(c == null)
            throw new MapperTransferException("The character Entity is NULL");

        if(dto.getName() != null)
            c.setName(dto.getName());
        if(dto.getActor() != null) {
            Actor actor = ModelFactory.initActor();
            actorMapper.transfer(dto.getActor(), actor);
            c.setActor(actor);
        }
    }

}
