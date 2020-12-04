package com.thigorqueiroz.fry.domain.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.UUID;

public abstract  class AggregateRootWithIdentifierAsUUID<T> extends AgregateRoot<T> implements Persistable<UUID> {

    @Id
    @JsonProperty("identifier")
    protected UUID id;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    public UUID getId() {
        return id;
    }

    public static class BeforeSaveListener implements ApplicationListener<BeforeSaveEvent> {

        @Override
        public void onApplicationEvent(BeforeSaveEvent event) {
            if (event.getEntity() instanceof AggregateRootWithIdentifierAsUUID) {
                AggregateRootWithIdentifierAsUUID entity = (AggregateRootWithIdentifierAsUUID) event.getEntity();
                if (entity.id == null) {
                    entity.id = UUID.randomUUID();
                }
            }
        }
    }

}
