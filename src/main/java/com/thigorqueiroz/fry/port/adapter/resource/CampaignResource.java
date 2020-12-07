package com.thigorqueiroz.fry.port.adapter.resource;


import com.thigorqueiroz.fry.application.command.CreateCampaignCommand;
import com.thigorqueiroz.fry.application.command.PartialUpdateCampaignCommand;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "campaigns", description = "Campaign API")
public class CampaignResource {
    private static final Logger Logger = LoggerFactory.getLogger(CampaignResource.class);
    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public List<Campaign> findAll() {
        Logger.info("find all campaigns");
        return campaignService.findAllWithDuration();
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "Find all campaigns by team id",
            description = "Find all campaigns by team id", tags = {"campaigns"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Campaign.class))))})
    public List<Campaign> findAll(@PathVariable UUID teamId) {
        Logger.info("find all campaigns");
        return campaignService.findAlldByTeam(teamId);
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
