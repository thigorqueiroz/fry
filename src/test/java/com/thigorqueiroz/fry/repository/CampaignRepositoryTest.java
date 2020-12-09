package com.thigorqueiroz.fry.repository;

import br.com.six2six.fixturefactory.Fixture;
import com.thigorqueiroz.fry.application.command.CreateCampaignCommand;
import com.thigorqueiroz.fry.application.service.CampaignService;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.campaign.CampaignRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CampaignRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    CampaignService campaignService;

    @Test
    void givenCampaign_ShouldInsertIt_ThenReturnPersistedCampaign() {
        var campaignFixture = (Campaign) Fixture.from(Campaign.class).gimme("default");
        var campaignCommand = new CreateCampaignCommand(campaignFixture.getName(), "25/10/2020", "28/10/2021");
        campaignService.create(campaignCommand);
        var campaign = campaignRepository.findByName(campaignFixture.getName());
        assertThat(campaign).isNotEmpty();
    }
}