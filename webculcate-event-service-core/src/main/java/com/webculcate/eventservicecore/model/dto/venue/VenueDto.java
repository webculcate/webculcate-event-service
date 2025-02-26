package com.webculcate.eventservicecore.model.dto.venue;

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
public class VenueDto {

    private Long venueId;

    private String venueName;

    private String venueAddress;

    private TimeLogDto timeLog;

    public static VenueDto initializeBlankVenueDto() {
        return VenueDto.builder()
                .timeLog(new TimeLogDto())
                .build();
    }
}
