package com.thigorqueiroz.fry.domain.model.campaign;


import com.thigorqueiroz.fry.domain.model.common.DomainEvent;

import java.util.List;
import java.util.UUID;

public final class SearchCampaignsTerminatedEvent implements DomainEvent {

    public final List<UUID> campaigns;
    private final UUID teamId;
    private final String partner;

    public SearchCampaignsTerminatedEvent(UUID teamId, List<UUID> campaigns, String partner) {
        this.teamId = teamId;
        this.campaigns = campaigns;
        this.partner = partner;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public String getPartner() {
        return partner;
    }

    @Override
    public String getApp() {
        return "fry";
    }

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }
}
