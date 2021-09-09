package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.movies.CrewMember;
import it.unifi.ing.stlab.movierentalmanager.model.movies.CrewRole;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class CrewMemberDao extends BaseDao<CrewMember> {

    public CrewMemberDao() {
        super(CrewMember.class);
    }

    @Transactional
    public void update(CrewMember cm) {
        CrewMember oldCM = getEm().find(CrewMember.class, cm.getId());
        oldCM.setName(cm.getName());
        oldCM.setBirthDate(cm.getBirthDate());
        oldCM.setCountry(cm.getCountry());
        oldCM.setBiography(cm.getBiography());
        oldCM.setRole(cm.getRole());
        oldCM.setMovies(cm.getMovies());
    }

    @Transactional
    // Il parametro 'role' non è necessario ma permette un ulteriore controllo della bontà della richiesta
    public CrewMember fetchCrewMemberWithMovies(Long id, CrewRole role) {
        TypedQuery<CrewMember> query = getEm().createQuery(
                "FROM CrewMember cm JOIN FETCH cm.movies WHERE cm.id = :id AND cm.role = :role",
                CrewMember.class
        ).setParameter("id", id)
         .setParameter("role", role);
        return query.getSingleResult();
    }

    @Transactional
    public List<CrewMember> retrieveCrewMembersByName(String name) {
        TypedQuery<CrewMember> query = getEm().createQuery(
                "FROM CrewMember cm WHERE cm.name LIKE CONCAT('%', :name, '%')",
                CrewMember.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    @Transactional
    public List<CrewMember> retrieveCrewMembersByNameAndRole(String name, CrewRole role) {
        TypedQuery<CrewMember> query = getEm().createQuery(
                "FROM CrewMember cm WHERE cm.name LIKE CONCAT('%', :name, '%') AND cm.role = :role",
                CrewMember.class
        ).setParameter("name", name)
         .setParameter("role", role);
        return query.getResultList();
    }


}
