package it.unifi.ing.stlab.movierentalmanager.components.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ActorDto implements Serializable {

    private String name;
    private Date birthDate;
    private String country;
    private String stageName;
    private List<Long> moviesIDs;
//    private List<CharacterDto> characters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<Long> getMoviesIDs() {
        return moviesIDs;
    }

    public void setMoviesIDs(List<Long> moviesIDs) {
        this.moviesIDs = moviesIDs;
    }

//    public List<CharacterDto> getCharacters() {
//        return characters;
//    }
//
//    public void setCharacters(List<CharacterDto> characters) {
//        this.characters = characters;
//    }
}
