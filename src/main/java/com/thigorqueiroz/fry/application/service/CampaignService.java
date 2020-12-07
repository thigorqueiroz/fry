package com.thigorqueiroz.fry.application.service;

import com.thigorqueiroz.fry.application.command.CreateCampaignCommand;
import com.thigorqueiroz.fry.application.command.PartialUpdateCampaignCommand;
import com.thigorqueiroz.fry.application.command.SendAllCampaignsByTeamCommand;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.campaign.CampaignRepository;
import com.thigorqueiroz.fry.domain.model.campaign.SearchCampaignsTerminatedEvent;
import com.thigorqueiroz.fry.domain.model.common.AggregateRootWithIdentifierAsUUID;
import com.thigorqueiroz.fry.domain.model.common.BusinessException;
import com.thigorqueiroz.fry.domain.model.common.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CampaignService {
    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    private final ApplicationEventPublisher eventPublisher;
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository, ApplicationEventPublisher eventPublisher) {
        this.campaignRepository = campaignRepository;
        this.eventPublisher = eventPublisher;
    }

    //TODO: review if it is necessary
    public Campaign findById(UUID id) {
        return campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campaign"));
    }

    @Transactional
    public void delete(UUID id) {
        //TODO: unhappy path
        campaignRepository.findById(id)
                .ifPresent(campaignRepository::delete);
    }

    @Transactional
    public Campaign partialUpdate(PartialUpdateCampaignCommand command, UUID identifier) {
        var campaign = campaignRepository.findById(identifier).map(
                c -> new Campaign(c, command.name)
        ).orElseThrow(() -> new EntityNotFoundException("Campaign", identifier));
        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign create(CreateCampaignCommand command) {
        campaignRepository.findByName(command.name)
                .ifPresent(f -> {
                    throw new BusinessException("Campaign is already created!");
                });
        var campaign = new Campaign(command.name);
        return this.campaignRepository.save(campaign);
    }

    @Transactional(readOnly = true)
    public List<Campaign> findAll() {
        //TODO: paginated
        List<Campaign> campaigns = new ArrayList<>();
        campaignRepository.findAll().forEach(campaigns::add);
        return campaigns;
    }

    @Transactional(readOnly = true)
    public void publishAllAssociatedWithTeam(SendAllCampaignsByTeamCommand command) {
        List<UUID> campaigns = campaignRepository.findAllRelatedWithTeam(command.teamId)
                .stream()
                .map(AggregateRootWithIdentifierAsUUID::getId)
                .collect(Collectors.toList());
        eventPublisher.publishEvent(new SearchCampaignsTerminatedEvent(command.teamId, campaigns, command.partner));
    }

    @Transactional(readOnly = true)
    public List<Campaign> findAllRelatedWithTeam(UUID teamId) {
        return campaignRepository.findAllRelatedWithTeam(teamId);
    }
}
