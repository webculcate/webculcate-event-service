package com.webculcate.eventservicecore.model.dto.scheduledevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.eventservicecore.constant.ScheduledEventStatus;
import com.webculcate.eventservicecore.model.dto.event.EventDto;
import com.webculcate.eventservicecore.model.dto.venue.VenueDto;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import com.webculcate.eventservicecore.model.entity.embedded.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledEventDto {

    private Long scheduledEventId;

    private EventDto event;

    private VenueDto venue;

    private TimeRange timeRange;

    private Set<String> organisedBy;

    private ScheduledEventStatus status;

    private TimeLog timeLog;

    public static ScheduledEventDto initializeBlankScheduledEventDto() {
        return ScheduledEventDto.builder()
                .event(new EventDto())
                .venue(new VenueDto())
                .timeRange(new TimeRange())
                .timeLog(new TimeLog())
                .build();
    }
}
