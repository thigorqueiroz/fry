package com.thigorqueiroz.fry.domain.model.campaign;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, UUID> {
    Optional<Campaign> findByName(String name);
    @Query("INSERT INTO")
    List<Campaign> findAllRelatedWithTeam(UUID teamId);
}
