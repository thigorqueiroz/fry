package com.thigorqueiroz.fry.resource;

import br.com.six2six.fixturefactory.Fixture;
import com.thigorqueiroz.fry.AbstractBaseTest;
import com.thigorqueiroz.fry.application.command.CreateCampaignCommand;
import com.thigorqueiroz.fry.application.service.CampaignService;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.port.adapter.resource.CampaignResource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CampaignResource.class)
class CampaignResourceTest extends AbstractBaseTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CampaignService serviceMock;

    @Test
    void findCampaign_whenDoNotExists_thenReturnNotFound() throws Exception {
        var campaignFixture = (Campaign)Fixture.from(Campaign.class).gimme("default");
        Mockito.when(serviceMock.findById(campaignFixture.getId())).thenReturn(campaignFixture);

        mockMvc.perform(MockMvcRequestBuilders.get("/campaigns/"+campaignFixture.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenNewCampaign_whenCreate_thenReturnCreated() throws Exception {
        var campaignFixture = (Campaign)Fixture.from(Campaign.class).gimme("default");
        var command = new CreateCampaignCommand(campaignFixture.getName(), "25/10/2020", "26/10/2020");
        Mockito.when(serviceMock.create(command)).thenReturn(campaignFixture);
        mockMvc.perform(MockMvcRequestBuilders.get("/campaigns/"+campaignFixture.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("name", equalTo(campaignFixture.getName())));
    }
}
