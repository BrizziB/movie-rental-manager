package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDao {

    @PersistenceContext
    private EntityManager em;

    public void addPerson(Person p) {
        em.persist(p);
    }

    public Person findPersonById(Long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAllPeople() {
        TypedQuery<Person> query = em.createQuery("FROM Person", Person.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    public void updatePerson(Person p) {
        Person oldPerson = em.find(Person.class, p.getId());
        oldPerson.setName(p.getName());
        oldPerson.setSurname(p.getSurname());
        oldPerson.setBirthDate(p.getBirthDate());
        oldPerson.setCountry(p.getCountry());
        oldPerson.setBiography(p.getBiography());
    }

    public void savePerson(Person p) {
        if(p.getId() != null)
            em.merge(p);
        else
            em.persist(p);
    }

    public int deletePersonById(Long id) {
        Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :id", Person.class)
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
