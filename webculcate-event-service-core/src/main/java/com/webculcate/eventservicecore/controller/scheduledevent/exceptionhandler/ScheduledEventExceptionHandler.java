package com.webculcate.eventservicecore.controller.scheduledevent.exceptionhandler;

import com.webculcate.eventservicecore.exception.scheduledevent.InvalidCreateEventScheduleRequestException;
import com.webculcate.eventservicecore.exception.scheduledevent.ScheduledEventNotAvailableException;
import com.webculcate.eventservicecore.exception.venue.VenueNotAvailableException;
import com.webculcate.eventservicecore.model.dto.general.ServiceExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ScheduledEventExceptionHandler {

    @ExceptionHandler(ScheduledEventNotAvailableException.class)
    public ResponseEntity<ServiceExceptionResponse> handleScheduledEventNotAvailableException(ScheduledEventNotAvailableException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        SCHEDULED_EVENT_NOT_AVAILABLE,
                        SCHEDULED_EVENT_NOT_AVAILABLE.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

    @ExceptionHandler(InvalidCreateEventScheduleRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidCreateEventScheduleRequestException(InvalidCreateEventScheduleRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_EVENT_SCHEDULE_CREATION_REQUEST,
                        INVALID_EVENT_SCHEDULE_CREATION_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

}
