package com.thigorqueiroz.fry.port.adapter.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thigorqueiroz.fry.application.command.SendAllCampaignsByTeamCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PartnerCreatedMessage implements Serializable {
    @JsonProperty
    private  String app;
    @JsonProperty
    private  UUID team;
    @JsonProperty
    private  UUID id;
    @JsonProperty
    private  String partner;

    public SendAllCampaignsByTeamCommand toSendAllCampaignsByTeamCommand() {
        return new SendAllCampaignsByTeamCommand(team, partner);
    }
}
