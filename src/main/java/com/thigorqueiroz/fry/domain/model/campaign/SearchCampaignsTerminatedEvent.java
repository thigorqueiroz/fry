package com.thigorqueiroz.fry.domain.model.campaign;


import com.thigorqueiroz.fry.domain.model.common.DomainEvent;

import java.util.List;
import java.util.UUID;

public final class SearchCampaignsTerminatedEvent implements DomainEvent {

    public final List<UUID> campaigns;

    public SearchCampaignsTerminatedEvent(List<UUID> campaigns) {
        this.campaigns = campaigns;
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
