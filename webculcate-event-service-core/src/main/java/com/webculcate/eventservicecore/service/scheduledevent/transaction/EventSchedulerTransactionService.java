package com.webculcate.eventservicecore.service.scheduledevent.transaction;

import com.webculcate.eventservicecore.exception.scheduledevent.ScheduledEventNotAvailableException;
import com.webculcate.eventservicecore.model.dto.scheduledevent.TimeRangeDto;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.repository.scheduledevent.ScheduledEventRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.SCHEDULED_EVENT_NOT_AVAILABLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventSchedulerTransactionService {

    private final ScheduledEventRepository repository;

    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<ScheduledEvent> fetchScheduledEvent(Long id) {
        return repository.findByScheduledEventId(id);
    }

    @Transactional(readOnly = true)
    public Optional<List<ScheduledEvent>> fetchAllScheduledEvent(TimeRangeDto timeRange) {
        return repository.findAll(timeRange.getStartTime(), timeRange.getEndTime());
    }

    @Transactional
    public ScheduledEvent saveScheduledEvent(ScheduledEvent newSchedule) {
        return repository.save(newSchedule);
    }

    @Transactional
    public ScheduledEvent saveScheduledEventAndRefresh(ScheduledEvent newSchedule) {
        ScheduledEvent savedScheduledEvent = repository.saveAndFlush(newSchedule);
        entityManager.refresh(savedScheduledEvent);
        return savedScheduledEvent;
    }

    @Transactional
    public ScheduledEvent fetchAndUpdateScheduledEvent(Long scheduledEventId, ScheduledEvent updatedScheduledEvent) {
        Optional<ScheduledEvent> optionalScheduledEvent = fetchScheduledEvent(scheduledEventId);
        ScheduledEvent fetchedScheduledEvent = optionalScheduledEvent
                .orElseThrow(() -> new ScheduledEventNotAvailableException(SCHEDULED_EVENT_NOT_AVAILABLE.getMessage()));
        fetchedScheduledEvent.setEvent(updatedScheduledEvent.getEvent());
        fetchedScheduledEvent.setVenue(updatedScheduledEvent.getVenue());
        fetchedScheduledEvent.setTimeRange(updatedScheduledEvent.getTimeRange());
        fetchedScheduledEvent.setOrganisedBy(updatedScheduledEvent.getOrganisedBy());
        fetchedScheduledEvent.setStatus(updatedScheduledEvent.getStatus());
        ScheduledEvent savedScheduledEvent = saveScheduledEventAndRefresh(fetchedScheduledEvent);
        return savedScheduledEvent;
    }

}
