package com.thigorqueiroz.fry.domain.model.common;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface DomainEvent extends Serializable {
    default ZonedDateTime occuredOn() {
        return ZonedDateTime.now();
    }
    String getApp();
    UUID getId();

}