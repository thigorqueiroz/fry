package com.thigorqueiroz.fry.port.adapter.resource;


import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thigorqueiroz.fry.application.service.CampaignService;

import java.util.UUID;

@RestController
@RequestMapping("/campaigns/{campaignId}")
public class CampaignResource {
    private static final Logger Logger = LoggerFactory.getLogger(CampaignResource.class);
    @Autowired private CampaignService campaignService;

    @GetMapping
    public Campaign findAllCampaign(@PathVariable UUID campaignId) {
        Logger.debug("find campaign by id");
        return campaignService.getById(campaignId);
    }

}
