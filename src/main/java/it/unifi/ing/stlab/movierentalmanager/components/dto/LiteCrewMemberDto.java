package it.unifi.ing.stlab.movierentalmanager.components.dto;

import it.unifi.ing.stlab.movierentalmanager.model.CrewMember;

import java.io.Serializable;
import java.util.Date;

public class LiteCrewMemberDto implements Serializable {

    private String name;
    private String surname;
    private Date birthDate;
    private String country;
    private String role;

    public static LiteCrewMemberDto mapCrewMemberToLiteCrewMember(CrewMember crewMember) {
        LiteCrewMemberDto liteMember = new LiteCrewMemberDto();

        liteMember.name = crewMember.getName();
        liteMember.surname = crewMember.getSurname();
        liteMember.birthDate = crewMember.getBirthDate();
        liteMember.country = crewMember.getCountry();
        liteMember.role = crewMember.getRole();

        return liteMember;
    }
}
