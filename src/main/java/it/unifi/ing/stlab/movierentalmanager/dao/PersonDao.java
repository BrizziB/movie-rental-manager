package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addPerson(Person p) {
        em.persist(p);
    }

    public Optional<Person> findPersonById(Long id) {
        return Optional.ofNullable(em.find(Person.class, id));
    }

    public List<Person> findAllPeople() {
        TypedQuery<Person> query = em.createQuery("FROM Person", Person.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    @Transactional
    public void updatePerson(Person p) {
        Person oldPerson = em.find(Person.class, p.getId());
        oldPerson.setName(p.getName());
        oldPerson.setSurname(p.getSurname());
        oldPerson.setBirthDate(p.getBirthDate());
        oldPerson.setCountry(p.getCountry());
        oldPerson.setBiography(p.getBiography());
    }

    @Transactional
    public void savePerson(Person p) {
        if(p.getId() != null)
            em.merge(p);
        else
            em.persist(p);
    }

    @Transactional
    public int deletePersonById(Long id) {
        Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :id", Person.class)
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
