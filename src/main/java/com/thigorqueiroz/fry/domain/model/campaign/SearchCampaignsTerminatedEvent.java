package com.thigorqueiroz.fry.domain.model.campaign;


import com.thigorqueiroz.fry.domain.model.common.DomainEvent;

import java.util.List;

public final class SearchCampaignsTerminatedEvent implements DomainEvent {

    public final List<Campaign> campaigns;

    public SearchCampaignsTerminatedEvent(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
