package com.webculcate.eventservicecore.model.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventCreationRequest {

    @NotNull
    @NotBlank
    private String eventName;

    private String eventDescription;

    @NotNull
    private Long createdBy;

}
