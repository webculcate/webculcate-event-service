package com.webculcate.eventservicecore.service.scheduledevent.impl;

import com.webculcate.eventservicecore.exception.scheduledevent.ScheduledEventNotAvailableException;
import com.webculcate.eventservicecore.model.dto.scheduledevent.*;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.repository.scheduledevent.ScheduledEventRepository;
import com.webculcate.eventservicecore.service.scheduledevent.IEventSchedulerService;
import com.webculcate.eventservicecore.service.scheduledevent.IScheduledEventDtoMapper;
import com.webculcate.eventservicecore.service.scheduledevent.factory.IScheduledEventFactory;
import com.webculcate.eventservicecore.service.scheduledevent.strategy.ICapacityUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.webculcate.eventservicecore.constant.APIMetadata.SCHEDULED_EVENT_CREATION;
import static com.webculcate.eventservicecore.constant.APIMetadata.SCHEDULED_EVENT_MODIFICATION;
import static com.webculcate.eventservicecore.constant.EventSchedulerStrategyType.DEFAULT_EVENT_SCHEDULER_SERVICE;
import static com.webculcate.eventservicecore.constant.ServiceExceptionType.SCHEDULED_EVENT_NOT_AVAILABLE;

@Slf4j
@Service(DEFAULT_EVENT_SCHEDULER_SERVICE)
@RequiredArgsConstructor
public class EventSchedulerService implements IEventSchedulerService {

    private final ScheduledEventRepository repository;

    private final IScheduledEventFactory scheduledEventFactory;

    private final IScheduledEventDtoMapper scheduledEventDtoMapper;

    private final Map<String, ICapacityUpdateService> capacityUpdateStrategy;

    @Override
    public ScheduledEventDto getScheduledEvent(Long id) {
        Optional<ScheduledEvent> optionalScheduledEvent = fetchScheduledEvent(id);
        ScheduledEvent fetchedScheduledEvent = optionalScheduledEvent
                .orElseThrow(() -> new ScheduledEventNotAvailableException(SCHEDULED_EVENT_NOT_AVAILABLE.getMessage()));
        return scheduledEventDtoMapper.mapToScheduledEventDto(fetchedScheduledEvent);
    }

    @Override
    public EventConflictResponse getEventConflicts(TimeRangeDto timeRange) {
        Optional<List<ScheduledEvent>> optionalScheduledEvents = repository.findAll(timeRange.getStartTime(), timeRange.getEndTime());
        return new EventConflictResponse(optionalScheduledEvents.orElse(Collections.EMPTY_LIST));
    }

    @Transactional
    private Optional<ScheduledEvent> fetchScheduledEvent(Long id) {
        return repository.findByScheduledEventId(id);
    }

    @Override
    public ScheduledEventResponse scheduleEvent(CreateEventScheduleRequest request) {
        ScheduledEvent newSchedule = scheduledEventFactory.generateScheduledEvent(request);
        ScheduledEvent savedSchedule = saveScheduledEvent(newSchedule);
        return new ScheduledEventResponse(scheduledEventDtoMapper.mapToScheduledEventDto(savedSchedule), SCHEDULED_EVENT_CREATION.getSuccessMessage());
    }

    @Transactional
    private ScheduledEvent saveScheduledEvent(ScheduledEvent newSchedule) {
        return repository.save(newSchedule);
    }

    @Override
    public ScheduledEventResponse updateEventSchedule(UpdateEventScheduleRequest request) {
        ScheduledEvent updatedScheduledEvent = scheduledEventFactory.generateScheduledEvent(request);
        ScheduledEvent savedScheduledEvent = fetchAndUpdateScheduledEvent(request.getScheduledEventId(), updatedScheduledEvent);
        return new ScheduledEventResponse(scheduledEventDtoMapper.mapToScheduledEventDto(savedScheduledEvent), SCHEDULED_EVENT_MODIFICATION.getSuccessMessage());
    }

    private ScheduledEvent fetchAndUpdateScheduledEvent(Long scheduledEventId, ScheduledEvent updatedScheduledEvent) {
        Optional<ScheduledEvent> optionalScheduledEvent = fetchScheduledEvent(scheduledEventId);
        ScheduledEvent fetchedScheduledEvent = optionalScheduledEvent
                .orElseThrow(() -> new ScheduledEventNotAvailableException(SCHEDULED_EVENT_NOT_AVAILABLE.getMessage()));
        fetchedScheduledEvent.setEvent(updatedScheduledEvent.getEvent());
        fetchedScheduledEvent.setVenue(updatedScheduledEvent.getVenue());
        fetchedScheduledEvent.setTimeRange(updatedScheduledEvent.getTimeRange());
        fetchedScheduledEvent.setOrganisedBy(updatedScheduledEvent.getOrganisedBy());
        fetchedScheduledEvent.setStatus(updatedScheduledEvent.getStatus());
        ScheduledEvent savedScheduledEvent = saveScheduledEvent(fetchedScheduledEvent);
        return savedScheduledEvent;
    }

    @Override
    public CapacityUpdateResponse updateCapacity(CapacityUpdateRequest request) {
        ICapacityUpdateService capacityUpdateService = capacityUpdateStrategy.get(request.getCapacityUpdateType());
        Optional<ScheduledEvent> optionalScheduledEvent = fetchScheduledEvent(request.getScheduledEventId());
        ScheduledEvent fetchedScheduledEvent = optionalScheduledEvent
                .orElseThrow(() -> new ScheduledEventNotAvailableException(SCHEDULED_EVENT_NOT_AVAILABLE.getMessage()));
        capacityUpdateService.validate(request, fetchedScheduledEvent);
        capacityUpdateService.performUpdate(request, fetchedScheduledEvent);
        ScheduledEvent savedScheduledEvent = saveScheduledEvent(fetchedScheduledEvent);
        return new CapacityUpdateResponse(savedScheduledEvent.getScheduledEventId(), savedScheduledEvent.getCapacity(), true);

    }

}
