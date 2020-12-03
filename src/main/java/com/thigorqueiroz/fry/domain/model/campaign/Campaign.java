package com.thigorqueiroz.fry.domain.model.campaign;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("campaign")
@JsonRootName("campaign")
public class Campaign {
    @Id
    @JsonProperty("identifier")
    UUID id = UUID.randomUUID();
    @JsonProperty("name")
    String name;
    OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate
    OffsetDateTime updatedAt = OffsetDateTime.now();
}