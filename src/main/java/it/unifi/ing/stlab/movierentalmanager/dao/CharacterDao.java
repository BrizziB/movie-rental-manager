package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Character;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CharacterDao {

    @PersistenceContext
    private EntityManager em;

    public void addCharacter(Character c) {
        em.persist(c);
    }

    public Character findCharacterById(Long id) {
        return em.find(Character.class, id);
    }

    public List<Character> findAllCharacters() {
        TypedQuery<Character> query = em.createQuery("FROM Character", Character.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    public void updateCharacter(Character c) {
        Character oldCharacter = em.find(Character.class, c.getId());
        oldCharacter.setName(c.getName());
        oldCharacter.setActor(c.getActor());
        oldCharacter.setActors(c.getActors());
        oldCharacter.setMovies(c.getMovies());
    }

    public void saveCharacter(Character c) {
        if(c.getId() != null)
            em.merge(c);
        else
            em.persist(c);
    }

    public int deleteCharacterById(Long Id) {
        Query query = em.createQuery("DELETE FROM Character c WHERE c.id = :id")
                        .setParameter("id", Id);
        return query.executeUpdate();
    }

}
