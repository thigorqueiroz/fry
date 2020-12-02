package com.thigorqueiroz.fry.application.service;

import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import com.thigorqueiroz.fry.domain.model.campaign.CampaignRepository;
import com.thigorqueiroz.fry.domain.model.common.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CampaignService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignService.class);

    @Autowired private CampaignRepository campaignRepository;

    public Campaign getById(UUID id){
      return campaignRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campaign"));
    }
}
