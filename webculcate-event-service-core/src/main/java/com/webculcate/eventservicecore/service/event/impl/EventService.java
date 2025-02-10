package com.webculcate.eventservicecore.service.event.impl;

import com.webculcate.eventservicecore.exception.event.EventNotAvailableException;
import com.webculcate.eventservicecore.exception.venue.VenueNotAvailableException;
import com.webculcate.eventservicecore.model.dto.event.*;
import com.webculcate.eventservicecore.model.dto.venue.VenueCreationResponse;
import com.webculcate.eventservicecore.model.dto.venue.VenueUpdateResponse;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.repository.event.EventRepository;
import com.webculcate.eventservicecore.repository.venue.VenueRepository;
import com.webculcate.eventservicecore.service.event.IEventDtoMapper;
import com.webculcate.eventservicecore.service.event.IEventService;
import com.webculcate.eventservicecore.service.event.factory.IEventFactory;
import com.webculcate.eventservicecore.service.venue.IVenueDtoMapper;
import com.webculcate.eventservicecore.service.venue.factory.IVenueFactory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.webculcate.eventservicecore.constant.EventServiceStrategyType.DEFAULT_EVENT_SERVICE;
import static com.webculcate.eventservicecore.constant.ServiceExceptionType.EVENT_NOT_AVAILABLE;
import static com.webculcate.eventservicecore.constant.ServiceExceptionType.VENUE_NOT_AVAILABLE;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.DEFAULT_VENUE_SERVICE;

@Slf4j
@Service(DEFAULT_EVENT_SERVICE)
@RequiredArgsConstructor
public class EventService implements IEventService {

    private final EventRepository repository;

    private final IEventFactory eventFactory;

    private final IEventDtoMapper eventDtoMapper;

    @Override
    public EventDto getEvent(Long id) {
        Optional<Event> optionalEvent = fetchEvent(id);
        Event fetchedEvent = optionalEvent
                .orElseThrow(() -> new EventNotAvailableException(EVENT_NOT_AVAILABLE.getMessage()));
        return eventDtoMapper.mapToEventDto(fetchedEvent);
    }

    @Transactional
    private Optional<Event> fetchEvent(Long id) {
        return repository.findByEventId(id);
    }

    @Override
    public EventCreationResponse createEvent(EventCreationRequest request) {
        Event newEvent = eventFactory.generateEvent(request);
        Event savedEvent = saveEvent(newEvent);
        return new EventCreationResponse(eventDtoMapper.mapToEventDto(savedEvent));
    }

    @Transactional
    private Event saveEvent(Event newEvent) {
        return repository.save(newEvent);
    }

    @Override
    public EventUpdateResponse updateEvent(EventUpdateRequest request) {
        Event updatedEvent = eventFactory.generateEvent(request);
        Event savedEvent = fetchAndUpdateEvent(request.getEventId(), updatedEvent);
        return new EventUpdateResponse(eventDtoMapper.mapToEventDto(savedEvent));
    }

    private Event fetchAndUpdateEvent(@NotNull @NotEmpty Long eventId, Event updatedEvent) {
        Optional<Event> optionalEvent = fetchEvent(eventId);
        Event fetchedEvent = optionalEvent
                .orElseThrow(() -> new EventNotAvailableException(EVENT_NOT_AVAILABLE.getMessage()));
        fetchedEvent.setEventName(updatedEvent.getEventName());
        fetchedEvent.setEventDescription(updatedEvent.getEventDescription());
        Event savedEvent = saveEvent(fetchedEvent);
        return savedEvent;
    }
}
