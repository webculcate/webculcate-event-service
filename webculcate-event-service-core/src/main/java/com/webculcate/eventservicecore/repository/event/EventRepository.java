package com.webculcate.eventservicecore.repository.event;

import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByEventId(Long eventId);

}
