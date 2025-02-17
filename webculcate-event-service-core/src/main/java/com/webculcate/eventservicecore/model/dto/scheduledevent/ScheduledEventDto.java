package com.webculcate.eventservicecore.model.dto.scheduledevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.eventservicecore.constant.ScheduledEventStatus;
import com.webculcate.eventservicecore.model.dto.event.EventDto;
import com.webculcate.eventservicecore.model.dto.general.TimeLogDto;
import com.webculcate.eventservicecore.model.dto.venue.VenueDto;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import com.webculcate.eventservicecore.model.entity.embedded.TimeRange;
import com.webculcate.eventservicecore.model.external.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    private List<UserDto> organisedBy;

    private ScheduledEventStatus status;

    private Integer capacity;

    private Integer maxCapacity;

    private TimeLogDto timeLog;

    public static ScheduledEventDto initializeBlankScheduledEventDto() {
        return ScheduledEventDto.builder()
                .event(EventDto.initializeBlankEventDto())
                .venue(VenueDto.initializeBlankVenueDto())
                .timeRange(new TimeRange())
                .timeLog(new TimeLogDto())
                .build();
    }
}
