package com.thigorqueiroz.fry.domain.model.duration;

import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.common.DomainEvent;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
public class DurationCampaignUpdatedEvent implements DomainEvent {

    private final Set<Campaign> campaigns;
    public DurationCampaignUpdatedEvent(Set<Campaign> campaigns) {
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

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }
}
