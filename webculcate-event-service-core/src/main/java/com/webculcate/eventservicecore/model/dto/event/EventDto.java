package com.webculcate.eventservicecore.model.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.eventservicecore.model.dto.general.TimeLogDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    private Long eventId;

    private String eventName;

    private String eventDescription;

    private Long createdBy;

    private TimeLogDto timeLog;

    public static EventDto initializeBlankEventDto() {
        return EventDto.builder()
                .timeLog(new TimeLogDto())
                .build();
    }
}
