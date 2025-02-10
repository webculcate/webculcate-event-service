package com.webculcate.eventservicecore.service.scheduledevent;

import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventDto;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;

public interface IScheduledEventDtoMapper {

    ScheduledEventDto mapToScheduledEventDto(ScheduledEvent savedSchedule);

}
