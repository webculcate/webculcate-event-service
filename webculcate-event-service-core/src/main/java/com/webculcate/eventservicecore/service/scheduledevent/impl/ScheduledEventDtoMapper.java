package com.webculcate.eventservicecore.service.scheduledevent.impl;

import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventDto;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.service.external.user.UserServiceExt;
import com.webculcate.eventservicecore.service.scheduledevent.IScheduledEventDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledEventDtoMapper implements IScheduledEventDtoMapper {

    private final UserServiceExt userService;

    @Override
    public ScheduledEventDto mapToScheduledEventDto(ScheduledEvent scheduledEvent) {
        ScheduledEventDto scheduledEventDto = ScheduledEventDto.initializeBlankScheduledEventDto();
        copyProperties(scheduledEvent, scheduledEventDto);
        if (nonNull(scheduledEvent.getEvent()) && nonNull(scheduledEventDto.getEvent())) {
            copyProperties(scheduledEvent.getEvent(), scheduledEventDto.getEvent());
            if (nonNull(scheduledEvent.getEvent().getTimeLog()) && nonNull(scheduledEventDto.getEvent().getTimeLog()))
                copyProperties(scheduledEvent.getEvent().getTimeLog(), scheduledEventDto.getEvent().getTimeLog());
        }
        if (nonNull(scheduledEvent.getVenue()) && nonNull(scheduledEventDto.getVenue())) {
            copyProperties(scheduledEvent.getVenue(), scheduledEventDto.getVenue());
            if (nonNull(scheduledEvent.getVenue().getTimeLog()) && nonNull(scheduledEventDto.getVenue().getTimeLog()))
                copyProperties(scheduledEvent.getVenue().getTimeLog(), scheduledEventDto.getVenue().getTimeLog());
        }
        if (nonNull(scheduledEvent.getTimeRange()) && nonNull(scheduledEventDto.getTimeRange()))
            copyProperties(scheduledEvent.getTimeRange(), scheduledEventDto.getTimeRange());
        if (nonNull(scheduledEvent.getTimeLog()) && nonNull(scheduledEventDto.getTimeLog()))
            copyProperties(scheduledEvent.getTimeLog(), scheduledEventDto.getTimeLog());
        scheduledEventDto.setOrganisedBy(userService.resolveUsers(scheduledEvent.getOrganisedBy()));
        return scheduledEventDto;
    }

}
