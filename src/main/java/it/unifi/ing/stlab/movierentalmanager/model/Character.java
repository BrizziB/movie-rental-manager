package it.unifi.ing.stlab.movierentalmanager.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "character")
public class Character extends BaseEntity{

    private String name;

    private List<Person> dubbers;

    private Person actor;

    public Character(){

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
