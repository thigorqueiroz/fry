package com.thigorqueiroz.fry.port.adapter.messaging;


import com.thigorqueiroz.fry.domain.model.common.DomainEvent;

import java.io.Serializable;
import java.util.UUID;

public final class PartnerCreatedEvent implements Serializable {
    UUID team;

    public PartnerCreatedEvent(UUID team) {
        this.team = team;
    }

    public UUID getTeam() {
        return team;
    }

    public void setTeam(UUID team) {
        this.team = team;
    }
}
