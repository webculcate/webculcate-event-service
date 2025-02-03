package com.webculcate.eventservicecore.service.scheduledevent.factory.impl;

import com.webculcate.eventservicecore.constant.ScheduledEventStatus;
import com.webculcate.eventservicecore.model.dto.scheduledevent.CreateEventScheduleRequest;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import com.webculcate.eventservicecore.model.entity.embedded.TimeRange;
import com.webculcate.eventservicecore.service.scheduledevent.factory.IScheduledEventFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultScheduledEventFactory implements IScheduledEventFactory {

    @Override
    public ScheduledEvent generateScheduledEvent(CreateEventScheduleRequest request) {
        return ScheduledEvent.builder()
                .event(new Event(request.getEventId()))
                .venue(new Venue(request.getVenueId()))
                .timeRange(new TimeRange(request.getTimeRange().getStartTime(), request.getTimeRange().getEndTime()))
                .organisedBy(request.getOrganisedBy())
                .status(ScheduledEventStatus.SCHEDULED)
                .timeLog(new TimeLog())
                .build();
    }

}
