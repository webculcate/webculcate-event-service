package com.webculcate.eventservicecore.service.scheduledevent;

import com.webculcate.eventservicecore.model.dto.scheduledevent.*;

public interface IEventSchedulerService {

    ScheduledEventResponse scheduleEvent(CreateEventScheduleRequest request);

    ScheduledEventDto getScheduledEvent(Long id);

    EventConflictResponse getEventConflicts(TimeRangeDto timeRange);

    ScheduledEventResponse updateEventSchedule(UpdateEventScheduleRequest request);

}
