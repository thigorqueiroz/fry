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
import com.thigorqueiroz.fry.domain.model.duration.Duration;
import com.thigorqueiroz.fry.domain.model.duration.DurationRepository;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log
public class CampaignService {

    private final ApplicationEventPublisher eventPublisher;
    private final CampaignRepository campaignRepository;
    private final DurationRepository durationRepository;

    public CampaignService(CampaignRepository campaignRepository,
                           DurationRepository durationRepository,
                           ApplicationEventPublisher eventPublisher) {
        this.campaignRepository = campaignRepository;
        this.durationRepository = durationRepository;
        this.eventPublisher = eventPublisher;
    }

    public Campaign findById(UUID id) {
        return campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campaign"));
    }

    @Transactional
    public void delete(UUID id) {
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
        List<Duration> durations = durationRepository.findAllByDate(command.getPeriodEnd());
        List<Duration> durationsPeriodEndEqual = durations.stream()
                .filter(duration -> duration.isPeriodEndEqual(command.getPeriodEnd()))
                .collect(Collectors.toList());
        List<Duration> durationsEqual = durations.stream()
                .filter(duration ->
                        duration.isDurationEqual(command.getPeriodStart(), command.getPeriodEnd()))
                .collect(Collectors.toList());

        durationsPeriodEndEqual.stream().map(duration -> duration.plusDayInPeriodEnd(1L)).forEach(durationRepository::save);
        durationsEqual.stream().map(duration -> duration.plusDayInPeriodEnd(2L)).forEach(durationRepository::save);
        var durationSaved = this.durationRepository.save(new Duration(command.getPeriodStart(), command.getPeriodEnd()));
        var campaign = new Campaign(command.name, durationSaved.getId());
        return this.campaignRepository.save(campaign);
    }

    @Transactional(readOnly = true)
    public List<Campaign> findAll() {
        return campaignRepository.findAll(LocalDate.now());
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
    public List<Campaign> findAlldByTeam(UUID teamId) {
        return campaignRepository.findAllRelatedWithTeam(teamId);
    }
}
