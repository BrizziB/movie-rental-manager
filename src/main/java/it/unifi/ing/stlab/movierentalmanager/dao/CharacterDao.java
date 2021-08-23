package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Character;

import javax.transaction.Transactional;

public class CharacterDao extends BaseDao<Character> {

    public CharacterDao() {
        super(Character.class);
    }

    @Transactional
    public void update(Character c) {
        Character oldCharacter = getEm().find(Character.class, c.getId());
        oldCharacter.setName(c.getName());
        oldCharacter.setActor(c.getActor());
        oldCharacter.setActors(c.getActors());
        oldCharacter.setMovies(c.getMovies());
    }

}
