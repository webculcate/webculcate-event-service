package com.webculcate.eventservicecore.model.dto.scheduledevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class CreateEventScheduleRequest {

    private Long eventId;

    private Long venueId;

    private TimeRangeDto timeRange;

    private Set<String> organisedBy;

}
