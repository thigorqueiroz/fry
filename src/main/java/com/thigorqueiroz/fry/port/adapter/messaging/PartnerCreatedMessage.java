package com.thigorqueiroz.fry.port.adapter.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thigorqueiroz.fry.application.command.SendAllCampaignsByTeamCommand;

import java.io.Serializable;
import java.util.UUID;


public class PartnerCreatedMessage implements Serializable {
    @JsonProperty("event")
    public PartnerCreatedEvent event;
    @JsonProperty("app")
    public String app;
    @JsonProperty("event_id")
    public UUID eventId;

    public PartnerCreatedMessage(PartnerCreatedEvent event, String app, UUID eventId) {
        this.event = event;
        this.app = app;
        this.eventId = eventId;
    }

    public PartnerCreatedEvent getEvent() {
        return event;
    }

    public void setEvent(PartnerCreatedEvent event) {
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

    public SendAllCampaignsByTeamCommand toSendAllCampaignsByTeamCommand() {
        return new SendAllCampaignsByTeamCommand(null);
    }
}
