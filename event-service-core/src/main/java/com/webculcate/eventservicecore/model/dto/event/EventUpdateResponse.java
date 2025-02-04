package com.webculcate.eventservicecore.model.dto.event;

import static com.webculcate.eventservicecore.constant.APIMetadata.EVENT_MODIFICATION;

public class EventUpdateResponse extends EventCreationResponse {

    public EventUpdateResponse(EventDto event) {
        super(EVENT_MODIFICATION.getSuccessMessage(), event);
    }

}
