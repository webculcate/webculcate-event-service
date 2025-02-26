package com.webculcate.eventservicecore.service.event.transaction;

import com.webculcate.eventservicecore.exception.event.EventNotAvailableException;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.EVENT_NOT_AVAILABLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventTransactionService {

    private final EventRepository repository;

    @Transactional(readOnly = true)
    public Optional<Event> fetchEvent(Long id) {
        return repository.findByEventId(id);
    }

    @Transactional
    public Event saveEvent(Event newEvent) {
        return repository.save(newEvent);
    }

    @Transactional
    public Event fetchAndUpdateEvent(Long eventId, Event updatedEvent) {
        Optional<Event> optionalEvent = fetchEvent(eventId);
        Event fetchedEvent = optionalEvent
                .orElseThrow(() -> new EventNotAvailableException(EVENT_NOT_AVAILABLE.getMessage()));
        fetchedEvent.setEventName(updatedEvent.getEventName());
        fetchedEvent.setEventDescription(updatedEvent.getEventDescription());
        Event savedEvent = saveEvent(fetchedEvent);
        return savedEvent;
    }

}
