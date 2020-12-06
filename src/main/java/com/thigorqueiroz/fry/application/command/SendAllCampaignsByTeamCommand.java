package com.thigorqueiroz.fry.application.command;

import java.util.UUID;

public class SendAllCampaignsByTeamCommand {
    public UUID teamId;

    public SendAllCampaignsByTeamCommand(UUID teamId) {
        this.teamId = teamId;
    }
}
