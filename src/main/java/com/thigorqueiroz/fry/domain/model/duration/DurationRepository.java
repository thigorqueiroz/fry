package com.thigorqueiroz.fry.domain.model.duration;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DurationRepository extends CrudRepository<Duration, UUID> {
    @Query(value = "SELECT * " +
            "FROM Duration d " +
            "JOIN Campaign c ON c.duration_id = d.id " +
            "WHERE d.period_end >= :date"
    )
    List<Duration> findAllByDate(@Param("date") LocalDate date);
}
