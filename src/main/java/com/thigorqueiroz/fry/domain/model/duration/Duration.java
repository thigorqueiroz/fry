package com.thigorqueiroz.fry.domain.model.duration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.common.AggregateRootWithIdentifierAsUUID;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Duration extends AggregateRootWithIdentifierAsUUID<Duration> {
    Date periodStart;
    Date periodEnd;
    OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate
    @JsonIgnore
    OffsetDateTime updatedAt = OffsetDateTime.now();

    @MappedCollection(idColumn = "duration_id")
    Set<Campaign> campaigns = new HashSet<>();

    public Duration() {
    }

    public Duration(Date periodStart, Date periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }
}
