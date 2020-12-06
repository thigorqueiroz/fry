package com.thigorqueiroz.fry.port.adapter.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thigorqueiroz.fry.application.command.SendAllCampaignsByTeamCommand;

import java.io.Serializable;
import java.util.UUID;


public class PartnerCreatedMessage implements Serializable {
    @JsonProperty
    public  String app;
    @JsonProperty
    public  UUID team;
    @JsonProperty
    public  UUID id;

    public PartnerCreatedMessage(){
        super();
    }

    public PartnerCreatedMessage(UUID team, String app, UUID id) {
        this.team = team;
        this.app = app;
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public UUID getTeam() {
        return team;
    }

    public UUID getEventId() {
        return id;
    }

    public SendAllCampaignsByTeamCommand toSendAllCampaignsByTeamCommand() {
        return new SendAllCampaignsByTeamCommand(null);
    }
}
