package com.thigorqueiroz.fry.domain.model.campaign;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("campaign")
public class Campaign extends AbstractAggregateRoot<Campaign> {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;

    //private RangeTime rangeTime;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    @LastModifiedDate
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    //CREATE TABLE campaign(
    //    id UUID PRIMARY KEY,
    //    name VARCHAR(255),
    //    range_time_id UUID,
    //    created_at TIMESTAMP WITH TIME ZONE ,
    //    updated_at TIMESTAMP WITH TIME ZONE,
    //    FOREIGN KEY(range_time_id) REFERENCES range_time(id)
    //);
}
