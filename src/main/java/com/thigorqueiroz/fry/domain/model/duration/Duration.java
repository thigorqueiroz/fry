package com.thigorqueiroz.fry.domain.model.duration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.common.AggregateRootWithIdentifierAsUUID;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

public class Duration extends AggregateRootWithIdentifierAsUUID<Duration> {
    LocalDate periodStart;
    LocalDate periodEnd;
    OffsetDateTime createdAt = OffsetDateTime.now();
    @LastModifiedDate
    @JsonIgnore
    OffsetDateTime updatedAt = OffsetDateTime.now();

    @MappedCollection(idColumn = "duration_id")
    Set<Campaign> campaigns = new HashSet<>();

    public Duration() {
    }

    public Duration plusDayInPeriodEnd(Long day){
  //      var instant = this.periodEnd;
    //    var localdatetime = LocalDateTime.from(instant).plusDays(1);
//        this.periodEnd = Date.from(localdatetime.toInstant(ZoneOffset.UTC)); //make immutable
        this.periodEnd.plusDays(1L);
        return this;
    }


    public boolean isPeriodEndEqual(@NotNull LocalDate date) {
        return this.periodEnd.isEqual(date);
    }


    public boolean isDurationEqual(@NotNull LocalDate periodStart, @NotNull LocalDate periodEnd) {
        return periodStart.isEqual(this.periodStart) || (periodStart.isBefore(this.periodEnd)
                && periodStart.isAfter(this.periodStart)
        );
    }

    public Duration(LocalDate periodStart, LocalDate periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }
}
