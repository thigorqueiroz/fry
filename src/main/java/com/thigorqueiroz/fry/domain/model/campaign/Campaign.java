package com.thigorqueiroz.fry.domain.model.campaign;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.thigorqueiroz.fry.domain.model.common.AggregateRootWithIdentifierAsUUID;
import com.thigorqueiroz.fry.domain.model.duration.Duration;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table("campaign")
@JsonRootName("campaign")
public class Campaign extends AggregateRootWithIdentifierAsUUID<Campaign> {

    @JsonProperty("name")
    String name;
    @JsonIgnore
    OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate
    @JsonIgnore
    OffsetDateTime updatedAt = OffsetDateTime.now();

    @Column("duration_id")
    UUID durationId;

    public Campaign() {
    }

    public Campaign(Campaign that, String name) {
        this(that.id, name);
    }

    public Campaign(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Campaign(String name, UUID durationId) {
        this.name = name;
        this.durationId = durationId;

    }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign)) return false;

        Campaign campaign = (Campaign) o;
        if (id != null ? !id.equals(campaign.id) : campaign.id != null) return false;
        if (name != null ? !name.equals(campaign.name) : campaign.name != null) return false;
        if (createdAt != null ? !createdAt.equals(campaign.createdAt) : campaign.createdAt != null) return false;
        return updatedAt != null ? updatedAt.equals(campaign.updatedAt) : campaign.updatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    public Campaign addDuration(Duration durationSaved) {
        this.id = durationId;
        return this;
    }
}
