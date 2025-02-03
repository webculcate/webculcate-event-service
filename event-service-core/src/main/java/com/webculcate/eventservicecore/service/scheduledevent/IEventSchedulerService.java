package com.webculcate.eventservicecore.service.scheduledevent;

import com.webculcate.eventservicecore.model.dto.scheduledevent.CreateEventScheduleRequest;
import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventDto;
import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventResponse;

public interface IEventSchedulerService {

    ScheduledEventResponse scheduleEvent(CreateEventScheduleRequest request);

    ScheduledEventDto getScheduledEvent(Long id);
}
