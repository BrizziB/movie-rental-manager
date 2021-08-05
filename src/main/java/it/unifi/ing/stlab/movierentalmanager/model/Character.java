package it.unifi.ing.stlab.movierentalmanager.model;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity{

    private String name;

    @ManyToMany
    @JoinTable(name="characters_dubbers",
            joinColumns=@JoinColumn(name="character_id"),
            inverseJoinColumns=@JoinColumn(name="person_id"))
    private List<Person> dubbers;

    @ManyToOne
    private Person actor;

    public Character(){
        super();
    }

    public Character(UUID uuid){
        super(uuid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getDubbers() {
        return dubbers;
    }

    public void setDubbers(List<Person> dubbers) {
        this.dubbers = dubbers;
    }

    public Person getActor() {
        return actor;
    }

    public void setActor(Person actor) {
        this.actor = actor;
    }
}
