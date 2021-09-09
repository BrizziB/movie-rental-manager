package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.movies.Person;

import javax.transaction.Transactional;

public class PersonDao extends BaseDao<Person> {

    public PersonDao() {
        super(Person.class);
    }

    @Transactional
    public void update(Person p) {
        Person oldPerson = getEm().find(Person.class, p.getId());
        oldPerson.setName(p.getName());
        oldPerson.setBirthDate(p.getBirthDate());
        oldPerson.setCountry(p.getCountry());
        oldPerson.setBiography(p.getBiography());
    }

}
