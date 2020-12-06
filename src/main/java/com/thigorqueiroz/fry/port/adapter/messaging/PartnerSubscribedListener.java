package com.thigorqueiroz.fry.port.adapter.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thigorqueiroz.fry.application.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.thigorqueiroz.fry.port.adapter.config.AmqpConfig.PARTNER_CREATED_QUEUE;

@Component
public class PartnerSubscribedListener {

    private final CampaignService campaignService;
    private final ObjectMapper objectMapper;

    public PartnerSubscribedListener(CampaignService campaignService, ObjectMapper objectMapper) {
        this.campaignService = campaignService;
        this.objectMapper = objectMapper;
    }

    private static final Logger log = LoggerFactory.getLogger(PartnerSubscribedListener.class);

    @RabbitListener(id = "partnerSubscribedListener", queues = {PARTNER_CREATED_QUEUE})
    public void onPartnerCreated(String message ) {
        log.info("Received a new message on partnerSubscribedListener '{}'", message);
        try {
            var partnerCreatedMessage = objectMapper.readValue(message, PartnerCreatedMessage.class);
            campaignService.sendAllRelatedWithTeam(partnerCreatedMessage.toSendAllCampaignsByTeamCommand());
        } catch (JsonProcessingException e) {
            log.error("Error during deserialize message '{}', ex: '{}'", message, e);
        }
    }
    }