package com.thigorqueiroz.fry.application.service;

import com.thigorqueiroz.fry.application.command.CreateCampaignCommand;
import com.thigorqueiroz.fry.application.command.PartialUpdateCampaignCommand;
import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.campaign.CampaignIdentifierGenerator;
import com.thigorqueiroz.fry.domain.model.campaign.CampaignRepository;
import com.thigorqueiroz.fry.domain.model.common.BusinessException;
import com.thigorqueiroz.fry.domain.model.common.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CampaignService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignService.class);

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    CampaignIdentifierGenerator identifierGenerator;


    //TODO: review if it is necessary
    public Campaign findById(UUID id) {
        return campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campaign"));
    }

    @Transactional
    public void delete(UUID id) {
        //TODO: unhappy path
        campaignRepository.findById(id)
                .ifPresent(f -> campaignRepository.delete(f));
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
}
