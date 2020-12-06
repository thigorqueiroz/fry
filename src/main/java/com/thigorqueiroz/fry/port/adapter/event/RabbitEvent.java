package com.thigorqueiroz.fry.port.adapter.event;


import com.thigorqueiroz.fry.domain.model.common.DomainEvent;

import java.io.Serializable;
import java.util.UUID;

class RabbitEvent implements Serializable {
    public DomainEvent event;
    public String app = "campaign";
    public UUID eventId = UUID.randomUUID();

    RabbitEvent(DomainEvent event) {
        this.event = event;
    }

    public DomainEvent getEvent() {
        return event;
    }

    public void setEvent(DomainEvent event) {
        this.event = event;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
