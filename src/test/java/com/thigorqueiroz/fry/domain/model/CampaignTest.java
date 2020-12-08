package com.thigorqueiroz.fry.domain.model;

import br.com.six2six.fixturefactory.Fixture;
import com.thigorqueiroz.fry.AbstractBaseTest;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CampaignTest extends AbstractBaseTest {

    @Test
    void givenCampaign_whenFixture_thenNotNull() {
        var campaignFixture = (Campaign)Fixture.from(Campaign.class).gimme("default");
        assertNotNull(campaignFixture);
    }
}
