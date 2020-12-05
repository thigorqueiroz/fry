package com.thigorqueiroz.fry.port.adapter.messaging;

import com.thigorqueiroz.fry.application.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thigorqueiroz.fry.port.adapter.config.AmqpConfig.PARTNER_CREATED_QUEUE;

@Component
public class PartnerSubscribedListener {

    @Autowired
    CampaignService campaignService;

    private static final Logger log = LoggerFactory.getLogger(PartnerSubscribedListener.class);

    @RabbitListener(id = "partnerSubscribedListener", queues = {PARTNER_CREATED_QUEUE})
    public void onPartnerCreated(String event ) {
        log.debug("##############FIRED############## '{}'", event);
    }

}
