package com.thigorqueiroz.fry.domain.model.campaign;


import com.thigorqueiroz.fry.domain.model.common.DomainEvent;
import lombok.Data;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public final class SearchCampaignsTerminatedEvent implements DomainEvent {

    private  List<UUID> campaigns;
    private UUID teamId;
    private String partner;

    public SearchCampaignsTerminatedEvent(UUID teamId, List<UUID> campaigns, String partner) {
        this.teamId = teamId;
        this.campaigns = campaigns;
        this.partner = partner;
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
