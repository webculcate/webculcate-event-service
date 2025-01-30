package com.webculcate.eventservicecore.model.dto.venue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class VenueUpdateRequest extends VenueCreationRequest {

    @NotNull
    @NotEmpty
    private Long venueId;

}
