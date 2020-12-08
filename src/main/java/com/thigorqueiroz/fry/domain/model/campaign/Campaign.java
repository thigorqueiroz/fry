package com.thigorqueiroz.fry.domain.model.campaign;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.thigorqueiroz.fry.domain.model.common.AggregateRootWithIdentifierAsUUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("campaign")
@JsonRootName("campaign")
@Data
@NoArgsConstructor
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
}
