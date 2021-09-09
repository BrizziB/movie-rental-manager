package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.movies.Director;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class DirectorDao extends BaseDao<Director> {

    public DirectorDao() {
        super(Director.class);
    }

    @Transactional
    public void update(Director d) {
        Director oldDirector = getEm().find(Director.class, d.getId());
        oldDirector.setName(d.getName());
        oldDirector.setBirthDate(d.getBirthDate());
        oldDirector.setCountry(d.getCountry());
        oldDirector.setBiography(d.getBiography());
        oldDirector.setMovies(d.getMovies());
    }

    @Transactional
    public Director fetchDirectorWithMovies(Long id) {
        TypedQuery<Director> query = getEm().createQuery(
                "FROM Director d JOIN FETCH d.movies WHERE d.id = :id",
                Director.class
        ).setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public List<Director> retrieveDirectorsByName(String name) {
        TypedQuery<Director> query = getEm().createQuery(
                "FROM Director d WHERE d.name LIKE CONCAT('%', :name, '%')",
                Director.class
        ).setParameter("name", name);
        return query.getResultList();
    }

}
