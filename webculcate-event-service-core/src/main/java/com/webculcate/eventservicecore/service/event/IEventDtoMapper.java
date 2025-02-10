package com.webculcate.eventservicecore.service.event;

import com.webculcate.eventservicecore.model.dto.event.EventDto;
import com.webculcate.eventservicecore.model.entity.Event;

public interface IEventDtoMapper {

    EventDto mapToEventDto(Event event);

}
