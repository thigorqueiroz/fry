package com.thigorqueiroz.fry.domain.model.campaign;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("campaign")
public class Campaign {
    @Id
    UUID id = UUID.randomUUID();
    String name;
    String periodStart;
    String periodEnd;
    OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate
    OffsetDateTime updatedAt = OffsetDateTime.now();
}


