package com.webculcate.eventservicecore.service.scheduledevent.impl;

import com.webculcate.eventservicecore.model.dto.event.EventDto;
import com.webculcate.eventservicecore.model.dto.scheduledevent.ScheduledEventDto;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.service.external.user.UserServiceExt;
import com.webculcate.eventservicecore.service.scheduledevent.IScheduledEventDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.webculcate.eventservicecore.constant.EventSchedulerStrategyType.DEFAULT_EVENT_SCHEDULER_SERVICE;
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
        copyProperties(scheduledEvent.getEvent(), scheduledEventDto.getEvent());
        copyProperties(scheduledEvent.getVenue(), scheduledEventDto.getVenue());
        copyProperties(scheduledEvent.getTimeRange(), scheduledEventDto.getTimeRange());
        copyProperties(scheduledEvent.getTimeLog(), scheduledEventDto.getTimeLog());
        scheduledEventDto.setOrganisedBy(userService.resolveUsers(scheduledEvent.getOrganisedBy()));
        return scheduledEventDto;
    }
}
