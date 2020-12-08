package com.thigorqueiroz.fry.domain.model.duration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.common.AggregateRootWithIdentifierAsUUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Duration extends AggregateRootWithIdentifierAsUUID<Duration> {

    LocalDate periodStart;
    LocalDate periodEnd;
    OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate
    @JsonIgnore
    OffsetDateTime updatedAt = OffsetDateTime.now();

    @MappedCollection(idColumn = "duration_id")
    Set<Campaign> campaigns = new HashSet<>();

    public Duration(LocalDate periodStart, LocalDate periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public Duration plusDayInPeriodEnd(@NonNull Long day){
        this.periodEnd = this.periodEnd.plusDays(day);
        this.updatedAt = OffsetDateTime.now();
        registerEvent(new DurationCampaignUpdatedEvent(campaigns));
        return this;
    }


    public boolean isPeriodEndEqual(@NonNull LocalDate date) {
        return this.periodEnd.isEqual(date);
    }


    public boolean isDurationEqual(@NonNull  LocalDate periodStart, @NonNull  LocalDate periodEnd) {
        return periodStart.isEqual(this.periodStart) || (periodStart.isBefore(this.periodEnd)
                && periodStart.isAfter(this.periodStart)
        );
    }

}
