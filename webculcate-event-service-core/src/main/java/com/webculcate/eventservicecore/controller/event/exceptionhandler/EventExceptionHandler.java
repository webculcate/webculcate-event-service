package com.webculcate.eventservicecore.controller.event.exceptionhandler;

import com.webculcate.eventservicecore.exception.event.EventNotAvailableException;
import com.webculcate.eventservicecore.exception.event.InvalidEventCreationRequestException;
import com.webculcate.eventservicecore.exception.venue.InvalidVenueCreationRequestException;
import com.webculcate.eventservicecore.exception.venue.VenueNotAvailableException;
import com.webculcate.eventservicecore.model.dto.general.ServiceExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(EventNotAvailableException.class)
    public ResponseEntity<ServiceExceptionResponse> handleEventNotAvailableException(EventNotAvailableException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        EVENT_NOT_AVAILABLE,
                        EVENT_NOT_AVAILABLE.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

    @ExceptionHandler(InvalidEventCreationRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidEventCreationRequestException(InvalidEventCreationRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_EVENT_CREATION_REQUEST,
                        INVALID_EVENT_CREATION_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

}
