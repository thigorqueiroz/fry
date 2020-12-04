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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/campaigns")
public class CampaignResource {
    private static final Logger Logger = LoggerFactory.getLogger(CampaignResource.class);
    @Autowired private CampaignService campaignService;

    @GetMapping
    public List<Campaign> findAll() {
        Logger.debug("find all campaigns");
        return campaignService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Campaign> delete(@PathVariable UUID id) {
        campaignService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public Campaign create(@RequestBody CreateCampaignCommand command) {
        return campaignService.create(command);
    }

    @PatchMapping("/{id}")
    public Campaign partialUpdate(@RequestBody PartialUpdateCampaignCommand command, @PathVariable UUID id) {
        return campaignService.partialUpdate(command, id);
    }
}
