package com.webculcate.eventservicecore.controller.venue.exceptionhandler;

import com.webculcate.eventservicecore.exception.venue.InvalidVenueCreationRequestException;
import com.webculcate.eventservicecore.exception.venue.VenueNotAvailableException;
import com.webculcate.eventservicecore.model.dto.general.ServiceExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.INVALID_VENUE_CREATION_REQUEST;
import static com.webculcate.eventservicecore.constant.ServiceExceptionType.VENUE_NOT_AVAILABLE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class VenueExceptionHandler {

    @ExceptionHandler(VenueNotAvailableException.class)
    public ResponseEntity<ServiceExceptionResponse> handleVenueNotAvailableException(VenueNotAvailableException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        VENUE_NOT_AVAILABLE,
                        VENUE_NOT_AVAILABLE.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

    @ExceptionHandler(InvalidVenueCreationRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidVenueCreationRequestException(InvalidVenueCreationRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_VENUE_CREATION_REQUEST,
                        INVALID_VENUE_CREATION_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

}
