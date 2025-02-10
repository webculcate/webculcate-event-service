package com.webculcate.eventservicecore.service.scheduledevent.factory;

import com.webculcate.eventservicecore.model.dto.scheduledevent.CreateEventScheduleRequest;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;

public interface IScheduledEventFactory {

    ScheduledEvent generateScheduledEvent(CreateEventScheduleRequest request);

}

