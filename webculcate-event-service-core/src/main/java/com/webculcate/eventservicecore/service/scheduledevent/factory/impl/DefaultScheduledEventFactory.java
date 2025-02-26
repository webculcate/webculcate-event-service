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

import static com.webculcate.eventservicecore.constant.ServiceConstant.ZERO_LONG;
import static com.webculcate.eventservicecore.utility.ServiceHelper.nullHandledExtraction;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultScheduledEventFactory implements IScheduledEventFactory {

    @Override
    public ScheduledEvent generateScheduledEvent(CreateEventScheduleRequest request) {
        Long startTime = nullHandledExtraction(() -> request.getTimeRange().getStartTime()).orElse(null);
        Long endTime = nullHandledExtraction(() -> request.getTimeRange().getEndTime()).orElse(null);
        return ScheduledEvent.builder()
                .event(new Event(request.getEventId(), ZERO_LONG))
                .venue(new Venue(request.getVenueId(), ZERO_LONG))
                .timeRange(new TimeRange(startTime, endTime))
                .organisedBy(request.getOrganisedBy())
                .status(ScheduledEventStatus.SCHEDULED)
                .timeLog(new TimeLog())
                .capacity(request.getCapacity())
                .maxCapacity(request.getMaxCapacity())
                .build();
    }

}
