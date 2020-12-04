package com.thigorqueiroz.fry.application.service;

import com.thigorqueiroz.fry.application.command.CreateCampaignCommand;
import com.thigorqueiroz.fry.application.command.PartialUpdateCampaignCommand;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.campaign.CampaignRepository;
import com.thigorqueiroz.fry.domain.model.common.BusinessException;
import com.thigorqueiroz.fry.domain.model.common.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CampaignService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignService.class);

    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign getById(UUID id) {
        return campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campaign"));
    }

    public void delete(UUID id) {
        campaignRepository.findById(id)
                .ifPresent(f -> campaignRepository.delete(f));
    }

    public Campaign partialUpdate(PartialUpdateCampaignCommand command) {
        var campaign = campaignRepository.findById(command.id).map(
                c -> new Campaign(c, command.name)
        ).orElseThrow(() -> new EntityNotFoundException("Campaign", command.id));
        return campaignRepository.save(campaign);
    }

    public Campaign create(CreateCampaignCommand command) {
        campaignRepository.findByName(command.name)
                .ifPresent(f -> {
                    throw new BusinessException("Campaign is already created!");
                });
        var campaign = new Campaign(UUID.randomUUID(), command.name, OffsetDateTime.now(), OffsetDateTime.now());
        return this.campaignRepository.save(campaign);
    }
}
