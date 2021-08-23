package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id @GeneratedValue private Long id;
    private UUID uuid;
    // @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    // @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;

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

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if(o == null)
            return false;
        if (!(o instanceof BaseEntity))
            return false;

        BaseEntity that = (BaseEntity) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
