package com.webculcate.eventservicecore.repository.venue;

import com.webculcate.eventservicecore.model.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    Optional<Venue> findByVenueId(Long venueId);

}
