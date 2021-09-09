package it.unifi.ing.stlab.movierentalmanager.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id @GeneratedValue @Column(unique = true) private Long id;
    private UUID uuid;
    @Temporal(TemporalType.TIMESTAMP) private Date creationTime;
    @Temporal(TemporalType.TIMESTAMP) private Date lastUpdateTime;
    private boolean disabled = false;

    public BaseEntity() {
        this.creationTime = new Date(System.currentTimeMillis());
        this.lastUpdateTime = new Date(System.currentTimeMillis());
    }

    public BaseEntity(UUID uuid) {
        this.uuid = uuid;
        this.creationTime = new Date(System.currentTimeMillis());
        this.lastUpdateTime = new Date(System.currentTimeMillis());
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

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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
