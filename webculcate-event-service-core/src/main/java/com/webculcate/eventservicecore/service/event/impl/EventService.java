package com.webculcate.eventservicecore.service.event.impl;

import com.webculcate.eventservicecore.exception.event.EventNotAvailableException;
import com.webculcate.eventservicecore.model.dto.event.*;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.service.event.IEventDtoMapper;
import com.webculcate.eventservicecore.service.event.IEventService;
import com.webculcate.eventservicecore.service.event.factory.IEventFactory;
import com.webculcate.eventservicecore.service.event.transaction.EventTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.webculcate.eventservicecore.constant.EventServiceStrategyType.DEFAULT_EVENT_SERVICE;
import static com.webculcate.eventservicecore.constant.ServiceExceptionType.EVENT_NOT_AVAILABLE;

@Slf4j
@Service(DEFAULT_EVENT_SERVICE)
@RequiredArgsConstructor
public class EventService implements IEventService {

    private final EventTransactionService eventTransactionService;

    private final IEventFactory eventFactory;

    private final IEventDtoMapper eventDtoMapper;

    @Override
    public EventDto getEvent(Long id) {
        Optional<Event> optionalEvent = eventTransactionService.fetchEvent(id);
        Event fetchedEvent = optionalEvent
                .orElseThrow(() -> new EventNotAvailableException(EVENT_NOT_AVAILABLE.getMessage()));
        log.info("Event fetched");
        return eventDtoMapper.mapToEventDto(fetchedEvent);
    }

    @Override
    public EventCreationResponse createEvent(EventCreationRequest request) {
        Event newEvent = eventFactory.generateEvent(request);
        Event savedEvent = eventTransactionService.saveEvent(newEvent);
        log.info("Event created");
        return new EventCreationResponse(eventDtoMapper.mapToEventDto(savedEvent));
    }

    @Override
    public EventUpdateResponse updateEvent(EventUpdateRequest request) {
        Event updatedEvent = eventFactory.generateEvent(request);
        Event savedEvent = eventTransactionService.fetchAndUpdateEvent(request.getEventId(), updatedEvent);
        log.info("Event updated");
        return new EventUpdateResponse(eventDtoMapper.mapToEventDto(savedEvent));
    }

}
