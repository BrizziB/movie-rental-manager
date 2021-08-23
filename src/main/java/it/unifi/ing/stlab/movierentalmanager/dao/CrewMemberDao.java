package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.CrewMember;

import javax.transaction.Transactional;

public class CrewMemberDao extends BaseDao<CrewMember> {

    public CrewMemberDao() {
        super(CrewMember.class);
    }

    @Transactional
    public void update(CrewMember cm) {
        CrewMember oldCM = getEm().find(CrewMember.class, cm.getId());
        oldCM.setName(cm.getName());
        oldCM.setSurname(cm.getSurname());
        oldCM.setBirthDate(cm.getBirthDate());
        oldCM.setCountry(cm.getCountry());
        oldCM.setBiography(cm.getBiography());
        oldCM.setRole(cm.getRole());
        oldCM.setMovies(cm.getMovies());
    }

}
