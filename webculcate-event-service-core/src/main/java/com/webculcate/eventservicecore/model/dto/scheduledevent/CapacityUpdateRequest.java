package com.webculcate.eventservicecore.model.dto.scheduledevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
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
public class CapacityUpdateRequest {

    @NotNull
    private Long scheduledEventId;

    @NotNull
    @Min(0)
    private Integer capacity;

    @NotNull
    @NotBlank
    private String capacityUpdateType;

}
