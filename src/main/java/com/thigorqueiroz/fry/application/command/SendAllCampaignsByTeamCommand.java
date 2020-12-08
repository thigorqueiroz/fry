package com.thigorqueiroz.fry.application.command;

import java.util.UUID;

public class SendAllCampaignsByTeamCommand {
    public UUID teamId;
    public String partner;

    public SendAllCampaignsByTeamCommand(UUID teamId, String partner) {
        this.teamId = teamId;
        this.partner = partner;
    }
}
