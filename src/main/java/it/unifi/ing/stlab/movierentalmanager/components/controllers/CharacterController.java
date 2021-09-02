package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCharacterDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.CharacterMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.CharacterDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.Character;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class CharacterController {

    @Inject private CharacterDao characterDao;
    @Inject private CharacterMapper characterMapper;
    @Inject private MovieDao movieDao;
    private Gson gson;

    public LiteCharacterDto getCharacterById(Long id) {
        Character character = characterDao.findById(id)
                                          .orElseThrow(
                                                  () -> new IllegalArgumentException("Character not found")
                                          );
        return characterMapper.convert(character);
    }

    public List<LiteCharacterDto> getCharactersByName(String name) {
        return characterDao.retrieveCharactersByName(name)
                           .stream()
                           .map(characterMapper::convert)
                           .collect(Collectors.toList());
    }

    public void addCharacterToDb(String json) {
        gson = new Gson();
        LiteCharacterDto dto = gson.fromJson(json, LiteCharacterDto.class);
        Character character = ModelFactory.initCharacter();
        characterMapper.transfer(dto, character);
        characterDao.add(character);
    }

    public void updateCharacterOnDb(String json, Long id) {
        gson = new Gson();
        LiteCharacterDto dto = gson.fromJson(json, LiteCharacterDto.class);
        Character oldCharacter = characterDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any character on database")
        );
        characterMapper.transfer(dto, oldCharacter);
        characterDao.update(oldCharacter);
    }

    public void disableCharacterOnDb(boolean disabled, Long id) {
        Character character = characterDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any character on database")
        );
        character.setDisabled(disabled);
        characterDao.save(character);
    }

}
