package com.webculcate.eventservicecore.service.event.impl.proxy;

import com.webculcate.eventservicecore.exception.event.InvalidEventCreationRequestException;
import com.webculcate.eventservicecore.exception.event.InvalidEventUpdateRequestException;
import com.webculcate.eventservicecore.model.dto.event.*;
import com.webculcate.eventservicecore.service.event.IEventService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.webculcate.eventservicecore.constant.EventServiceStrategyType.EVENT_SERVICE_PROXY;
import static com.webculcate.eventservicecore.constant.ServiceConstant.STRING_SPACE;

@Slf4j
@Service(EVENT_SERVICE_PROXY)
@RequiredArgsConstructor
public class EventServiceProxy implements IEventService {

    private final Validator serviceValidator;

    private final IEventService eventService;

    @Override
    public EventCreationResponse createEvent(EventCreationRequest request) {
        Set<ConstraintViolation<EventCreationRequest>> validationResults = serviceValidator.validate(request);
        if (!validationResults.isEmpty()) {
            List<String> errorMessageList = generateErrorMessageList(validationResults);
            throw new InvalidEventCreationRequestException(errorMessageList);
        }
        log.info("Validation successful for createEvent");
        return eventService.createEvent(request);
    }

    @Override
    public EventDto getEvent(Long id) {
        return eventService.getEvent(id);
    }

    @Override
    public EventUpdateResponse updateEvent(EventUpdateRequest request) {
        Set<ConstraintViolation<EventUpdateRequest>> validationResults = serviceValidator.validate(request);
        if (!validationResults.isEmpty()) {
            List<String> errorMessageList = generateErrorMessageList(validationResults);
            throw new InvalidEventUpdateRequestException(errorMessageList);
        }
        log.info("Validation successful for updateEvent");
        return eventService.updateEvent(request);
    }

    private <T> List<String> generateErrorMessageList(Set<ConstraintViolation<T>> validationResults) {
        return validationResults.stream()
                .map(result -> result.getPropertyPath() + STRING_SPACE + result.getMessage())
                .toList();
    }
}
