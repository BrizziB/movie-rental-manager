package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.movies.Character;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
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

    @Transactional
    public Character fetchCharacterWithMovies(Long id) {
        TypedQuery<Character> query = getEm().createQuery(
                "FROM Character c JOIN FETCH c.movies WHERE c.id = :id",
                Character.class
        ).setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public List<Character> retrieveCharactersByName(String name) {
        TypedQuery<Character> query = getEm().createQuery(
                "FROM Character c WHERE c.name LIKE CONCAT('%', :name, '%')",
                Character.class
        ).setParameter("name", name);
        return query.getResultList();
    }

}
