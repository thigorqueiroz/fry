package com.thigorqueiroz.fry.domain.model.campaign;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("range_time")
public class RangeTime {

    @Id
     UUID id = UUID.randomUUID();
     String periodStart;
     String periodEnd;
     OffsetDateTime createdAt = OffsetDateTime.now();
     OffsetDateTime updatedAt = OffsetDateTime.now();

}
