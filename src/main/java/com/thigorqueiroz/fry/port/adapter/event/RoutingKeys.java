package com.thigorqueiroz.fry.port.adapter.event;

import com.thigorqueiroz.fry.domain.model.campaign.SearchCampaignsTerminatedEvent;

import java.util.HashMap;
import java.util.Map;

public class RoutingKeys {
   private static Map<Object, String> routingKeys = new HashMap<>();
    public static Map<Object, String> routingKeys () {
        routingKeys.put(SearchCampaignsTerminatedEvent.class, "resource.campaign.searched");
        return routingKeys;
    }
}
