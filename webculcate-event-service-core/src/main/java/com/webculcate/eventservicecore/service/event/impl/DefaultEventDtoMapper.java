package com.webculcate.eventservicecore.service.event.impl;

import com.webculcate.eventservicecore.model.dto.event.EventDto;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.service.event.IEventDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEventDtoMapper implements IEventDtoMapper {

    @Override
    public EventDto mapToEventDto(Event event) {
        EventDto eventDto = EventDto.initializeBlankEventDto();
        copyProperties(event, eventDto);
        if (nonNull(event.getTimeLog()) && nonNull(eventDto.getTimeLog()))
            copyProperties(event.getTimeLog(), eventDto.getTimeLog());
        return eventDto;
    }

}
