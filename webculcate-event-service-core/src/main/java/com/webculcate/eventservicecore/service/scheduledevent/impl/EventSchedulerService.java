package com.webculcate.eventservicecore.service.scheduledevent.impl;

import com.webculcate.eventservicecore.exception.scheduledevent.ScheduledEventNotAvailableException;
import com.webculcate.eventservicecore.model.dto.scheduledevent.*;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.service.scheduledevent.IEventSchedulerService;
import com.webculcate.eventservicecore.service.scheduledevent.IScheduledEventDtoMapper;
import com.webculcate.eventservicecore.service.scheduledevent.factory.IScheduledEventFactory;
import com.webculcate.eventservicecore.service.scheduledevent.strategy.ICapacityUpdateService;
import com.webculcate.eventservicecore.service.scheduledevent.transaction.EventSchedulerTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    private final EventSchedulerTransactionService eventSchedulerTransactionService;

    private final IScheduledEventFactory scheduledEventFactory;

    private final IScheduledEventDtoMapper scheduledEventDtoMapper;

    private final Map<String, ICapacityUpdateService> capacityUpdateStrategy;

    @Override
    public ScheduledEventDto getScheduledEvent(Long id) {
        Optional<ScheduledEvent> optionalScheduledEvent = eventSchedulerTransactionService.fetchScheduledEvent(id);
        ScheduledEvent fetchedScheduledEvent = optionalScheduledEvent
                .orElseThrow(() -> new ScheduledEventNotAvailableException(SCHEDULED_EVENT_NOT_AVAILABLE.getMessage()));
        log.info("Event schedule fetched");
        return scheduledEventDtoMapper.mapToScheduledEventDto(fetchedScheduledEvent);
    }

    @Override
    public EventConflictResponse getEventConflicts(TimeRangeDto timeRange) {
        Optional<List<ScheduledEvent>> optionalScheduledEvents = eventSchedulerTransactionService.fetchAllScheduledEvent(timeRange);
        return new EventConflictResponse(optionalScheduledEvents.orElse(Collections.EMPTY_LIST));
    }

    @Override
    public ScheduledEventResponse scheduleEvent(CreateEventScheduleRequest request) {
        ScheduledEvent newSchedule = scheduledEventFactory.generateScheduledEvent(request);
        ScheduledEvent savedSchedule = eventSchedulerTransactionService.saveScheduledEventAndRefresh(newSchedule);
        log.info("Event schedule created");
        return new ScheduledEventResponse(scheduledEventDtoMapper.mapToScheduledEventDto(savedSchedule), SCHEDULED_EVENT_CREATION.getSuccessMessage());
    }

    @Override
    public ScheduledEventResponse updateEventSchedule(UpdateEventScheduleRequest request) {
        ScheduledEvent updatedScheduledEvent = scheduledEventFactory.generateScheduledEvent(request);
        ScheduledEvent savedScheduledEvent = eventSchedulerTransactionService.fetchAndUpdateScheduledEvent(request.getScheduledEventId(), updatedScheduledEvent);
        log.info("Event schedule updated");
        return new ScheduledEventResponse(scheduledEventDtoMapper.mapToScheduledEventDto(savedScheduledEvent), SCHEDULED_EVENT_MODIFICATION.getSuccessMessage());
    }

    @Override
    public CapacityUpdateResponse updateCapacity(CapacityUpdateRequest request) {
        ICapacityUpdateService capacityUpdateService = capacityUpdateStrategy.get(request.getCapacityUpdateType());
        Optional<ScheduledEvent> optionalScheduledEvent = eventSchedulerTransactionService.fetchScheduledEvent(request.getScheduledEventId());
        ScheduledEvent fetchedScheduledEvent = optionalScheduledEvent
                .orElseThrow(() -> new ScheduledEventNotAvailableException(SCHEDULED_EVENT_NOT_AVAILABLE.getMessage()));
        Integer capacityBeforeUpdate = fetchedScheduledEvent.getCapacity();
        capacityUpdateService.validate(request, fetchedScheduledEvent);
        capacityUpdateService.performUpdate(request, fetchedScheduledEvent);
        ScheduledEvent savedScheduledEvent = eventSchedulerTransactionService.saveScheduledEvent(fetchedScheduledEvent);
        log.info("Capacity updated");
        return new CapacityUpdateResponse(savedScheduledEvent.getScheduledEventId(),
                capacityBeforeUpdate,
                savedScheduledEvent.getCapacity(),
                true);

    }

}
