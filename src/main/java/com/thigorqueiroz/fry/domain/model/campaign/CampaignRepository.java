package com.thigorqueiroz.fry.domain.model.campaign;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CampaignRepository extends PagingAndSortingRepository<Campaign, UUID> {
    Optional<Campaign> findByName(String name);

    @Query(value = "SELECT * " +
            "FROM Campaign c " +
            "INNER JOIN team_campaign tc ON c.id = tc.campaign_id " +
            "WHERE tc.team_id = :teamId")
    List<Campaign> findAllRelatedWithTeam(@Param("teamId") UUID teamId);

    @Query(value = "SELECT * " +
            "FROM Campaign c " +
            "JOIN Duration d ON c.duration_id = d.id " +
            "WHERE d.period_end >= :date"
    )
    List<Campaign> findAll(@Param("date") LocalDate date);
    //TODO: PAGINATE ALL


}
