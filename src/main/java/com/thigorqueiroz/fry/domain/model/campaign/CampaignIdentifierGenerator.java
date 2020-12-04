package com.thigorqueiroz.fry.domain.model.campaign;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CampaignIdentifierGenerator {
    public UUID generate() {
        return UUID.randomUUID();
    }
}
