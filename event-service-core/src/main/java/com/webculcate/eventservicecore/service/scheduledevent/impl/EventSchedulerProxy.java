package com.webculcate.eventservicecore.service.scheduledevent.impl;

import com.webculcate.eventservicecore.model.dto.scheduledevent.CreateEventScheduleRequest;
import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventDto;
import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventResponse;
import com.webculcate.eventservicecore.service.scheduledevent.IEventSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.webculcate.eventservicecore.constant.EventSchedulerStrategyType.EVENT_SCHEDULER_SERVICE_PROXY;
import static com.webculcate.eventservicecore.constant.EventServiceStrategyType.EVENT_SERVICE_PROXY;

@Slf4j
@Service(EVENT_SCHEDULER_SERVICE_PROXY)
@RequiredArgsConstructor
public class EventSchedulerProxy implements IEventSchedulerService {

    @Override
    public ScheduledEventResponse scheduleEvent(CreateEventScheduleRequest request) {
        return null;
    }

    @Override
    public ScheduledEventDto getScheduledEvent(Long id) {
        return null;
    }

}
