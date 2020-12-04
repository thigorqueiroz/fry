package com.thigorqueiroz.fry.port.adapter.config;

import com.thigorqueiroz.fry.domain.model.campaign.Campaign;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

@Configuration
public class PersistenceConfig {

    @Bean
    public ApplicationListener<BeforeSaveEvent> UUIDGenerator() {

        return event -> {
            var entity = event.getEntity();
            if (entity instanceof Campaign) {
                ((Campaign) entity).setId();
            }
        };
    }
}
