package com.webculcate.eventservicecore.service.scheduledevent.impl;

import com.webculcate.eventservicecore.model.dto.scheduledevent.CreateEventScheduleRequest;
import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventDto;
import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventResponse;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.repository.scheduledevent.ScheduledEventRepository;
import com.webculcate.eventservicecore.service.scheduledevent.IEventSchedulerService;
import com.webculcate.eventservicecore.service.scheduledevent.IScheduledEventDtoMapper;
import com.webculcate.eventservicecore.service.scheduledevent.factory.IScheduledEventFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.webculcate.eventservicecore.constant.EventSchedulerStrategyType.DEFAULT_EVENT_SCHEDULER_SERVICE;

@Slf4j
@Service(DEFAULT_EVENT_SCHEDULER_SERVICE)
@RequiredArgsConstructor
public class EventSchedulerService implements IEventSchedulerService {

    private final ScheduledEventRepository repository;

    private final IScheduledEventFactory scheduledEventFactory;

    private final IScheduledEventDtoMapper scheduledEventDtoMapper;

    @Override
    public ScheduledEventDto getScheduledEvent(Long id) {
        return null;
    }

    @Override
    public ScheduledEventResponse scheduleEvent(CreateEventScheduleRequest request) {
        ScheduledEvent newSchedule = scheduledEventFactory.generateScheduledEvent(request);
        ScheduledEvent savedSchedule = saveScheduledEvent(newSchedule);
        return new ScheduledEventResponse(scheduledEventDtoMapper.mapToScheduledEventDto(savedSchedule), "");
    }

    @Transactional
    private ScheduledEvent saveScheduledEvent(ScheduledEvent newSchedule) {
        return repository.save(newSchedule);
    }

}
