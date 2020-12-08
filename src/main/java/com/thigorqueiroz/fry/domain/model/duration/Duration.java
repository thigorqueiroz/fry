package com.thigorqueiroz.fry.domain.model.duration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.common.AggregateRootWithIdentifierAsUUID;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

    public Duration plusDayInPeriodEnd(Long day){
        var instant = this.periodEnd.toInstant();
        var localdatetime = LocalDateTime.from(instant).plusDays(1);
        this.periodEnd = Date.from(localdatetime.toInstant(ZoneOffset.UTC)); //make immutable
        return this;
    }


    public boolean isPeriodEndEqual(Date date) {
        return LocalDate.from(this.periodEnd.toInstant()).isEqual(LocalDate.from(date.toInstant()));
    }


    public boolean isDurationEqual(@NotNull Date periodStart, @NotNull Date periodEnd) {

        var startInstance = LocalDate.from(periodStart.toInstant());
        var endInstance = LocalDate.from(periodEnd.toInstant());
        var startParam = LocalDate.from(periodStart.toInstant());


        //TODO: validate this rule
        return startParam.isEqual(startInstance) || (startParam.isBefore(endInstance)
                && startParam.isAfter(startInstance)
        );
    }

    public Duration(Date periodStart, Date periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }
}
