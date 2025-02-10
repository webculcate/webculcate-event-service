package com.webculcate.eventservicecore.service.scheduledevent.impl.proxy;

import com.webculcate.eventservicecore.exception.scheduledevent.InvalidCreateEventScheduleRequestException;
import com.webculcate.eventservicecore.model.dto.scheduledevent.*;
import com.webculcate.eventservicecore.service.scheduledevent.IEventSchedulerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.webculcate.eventservicecore.constant.EventSchedulerStrategyType.EVENT_SCHEDULER_SERVICE_PROXY;
import static com.webculcate.eventservicecore.constant.ServiceConstant.STRING_SPACE;

@Slf4j
@Service(EVENT_SCHEDULER_SERVICE_PROXY)
@RequiredArgsConstructor
public class EventSchedulerProxy implements IEventSchedulerService {

    private final Validator serviceValidator;

    private final IEventSchedulerService eventSchedulerService;

    @Override
    public ScheduledEventResponse scheduleEvent(CreateEventScheduleRequest request) {
        Set<ConstraintViolation<CreateEventScheduleRequest>> validationResults = serviceValidator.validate(request);
        if (!validationResults.isEmpty()) {
            List<String> errorMessageList = validationResults.stream()
                    .map(result -> result.getPropertyPath() + STRING_SPACE + result.getMessage())
                    .toList();
            throw new InvalidCreateEventScheduleRequestException(errorMessageList);
        }
        return eventSchedulerService.scheduleEvent(request);
    }

    @Override
    public ScheduledEventDto getScheduledEvent(Long id) {
        return eventSchedulerService.getScheduledEvent(id);
    }

    @Override
    public EventConflictResponse getEventConflicts(TimeRangeDto timeRange) {
        return eventSchedulerService.getEventConflicts(timeRange);
    }

    @Override
    public ScheduledEventResponse updateEventSchedule(UpdateEventScheduleRequest request) {
        return eventSchedulerService.updateEventSchedule(request);
    }

}
