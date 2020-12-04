package com.thigorqueiroz.fry.port.adapter.resource;


import com.thigorqueiroz.fry.application.command.CreateCampaignCommand;
import com.thigorqueiroz.fry.application.command.PartialUpdateCampaignCommand;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thigorqueiroz.fry.application.service.CampaignService;

import java.util.UUID;

@RestController
@RequestMapping("/campaigns")
public class CampaignResource {
    private static final Logger Logger = LoggerFactory.getLogger(CampaignResource.class);
    @Autowired private CampaignService campaignService;

    @GetMapping
    @RequestMapping("/{id}")
    public Campaign findById(@PathVariable UUID id) {
        Logger.debug("find campaign by id");
        return campaignService.getById(id);
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Campaign> delete(@PathVariable UUID id) {
         campaignService.delete(id);
         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public Campaign create(@RequestBody CreateCampaignCommand command) {
        return campaignService.create(command);
    }

    @PatchMapping
    public Campaign partialUpdate(@RequestBody PartialUpdateCampaignCommand command) {
         return campaignService.partialUpdate(command);
    }
}
