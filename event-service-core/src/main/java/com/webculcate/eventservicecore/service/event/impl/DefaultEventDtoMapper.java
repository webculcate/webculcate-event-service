package com.webculcate.eventservicecore.service.event.impl;

import com.webculcate.eventservicecore.model.dto.event.EventDto;
import com.webculcate.eventservicecore.model.dto.venue.VenueDto;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.service.event.IEventDtoMapper;

import static org.springframework.beans.BeanUtils.copyProperties;

public class DefaultEventDtoMapper implements IEventDtoMapper {

    @Override
    public EventDto mapToEventDto(Event event) {
        EventDto eventDto = EventDto.initializeBlankEventDto();
        copyProperties(event, eventDto);
        copyProperties(event.getTimeLog(), eventDto.getTimeLog());
        return eventDto;
    }

}
