package com.webculcate.eventservicecore.controller.scheduledevent.exceptionhandler;

import com.webculcate.eventservicecore.exception.scheduledevent.*;
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

    @ExceptionHandler(InvalidUpdateEventScheduleRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidUpdateEventScheduleRequestException(InvalidUpdateEventScheduleRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_EVENT_SCHEDULE_UPDATE_REQUEST,
                        INVALID_EVENT_SCHEDULE_UPDATE_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

    @ExceptionHandler(InvalidCapacityUpdateRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidCapacityUpdateRequestException(InvalidCapacityUpdateRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_CAPACITY_UPDATE_REQUEST,
                        INVALID_CAPACITY_UPDATE_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

    @ExceptionHandler(InvalidTimeRangeRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidTimeRangeRequestException(InvalidTimeRangeRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_TIME_RANGE_REQUEST,
                        INVALID_TIME_RANGE_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

}
