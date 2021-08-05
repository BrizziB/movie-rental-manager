package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid;
    private Timestamp creationTime;
    private Timestamp lastUpdateTime;

    public BaseEntity() {
        this.creationTime = new Timestamp(System.currentTimeMillis());
        this.lastUpdateTime = new Timestamp(System.currentTimeMillis());
    }

    public BaseEntity(UUID uuid) {
        this.uuid = uuid;
        this.creationTime = new Timestamp(System.currentTimeMillis());
        this.lastUpdateTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
