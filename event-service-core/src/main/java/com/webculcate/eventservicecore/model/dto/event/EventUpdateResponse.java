package com.webculcate.eventservicecore.model.dto.event;

import static com.webculcate.eventservicecore.constant.APIMetadata.EVENT_MODIFICATION;

public class EventUpdateResponse extends EventCreationResponse {

    public EventUpdateResponse(EventDto venue) {
        super(EVENT_MODIFICATION.getSuccessMessage(), venue);
    }

}
