package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/* Se il modello di dominio non varia è meglio risolvere il problema delle sottoclassi con instanceof; altrimenti potrebbe
* essere una buona idea crea un'interfaccia PersonDao implementata poi da DAO concreti */

public class PersonDao extends BaseDao<Person> {

    public PersonDao() {
        super(Person.class);
    }

    @Transactional
    public void update(Person p) {
        Person oldPerson = getEm().find(Person.class, p.getId());
        oldPerson.setName(p.getName());
        oldPerson.setSurname(p.getSurname());
        oldPerson.setBirthDate(p.getBirthDate());
        oldPerson.setCountry(p.getCountry());
        oldPerson.setBiography(p.getBiography());
    }

}
