package com.webculcate.eventservicecore.model.dto.scheduledevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long eventId;

    @NotNull
    private Long venueId;

    @NotNull
    @Valid
    private TimeRangeDto timeRange;

    @NotNull
    @NotEmpty
    private Set<Long> organisedBy;

    @NotNull
    @Min(0)
    private Integer capacity;

    @NotNull
    @Min(0)
    private Integer maxCapacity;

}
