package com.thigorqueiroz.fry.domain.model.duration;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DurationRepository extends CrudRepository<Duration, UUID> {

}
