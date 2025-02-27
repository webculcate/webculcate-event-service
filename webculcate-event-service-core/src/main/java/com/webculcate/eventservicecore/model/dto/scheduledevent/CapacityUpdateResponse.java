package com.webculcate.eventservicecore.model.dto.scheduledevent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapacityUpdateResponse {

    private Long scheduledEventId;

    private Integer capacityBeforeUpdate;

    private Integer capacityAfterUpdate;

    private Boolean success;

}
