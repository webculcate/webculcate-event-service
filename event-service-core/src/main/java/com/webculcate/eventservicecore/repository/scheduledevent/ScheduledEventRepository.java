package com.webculcate.eventservicecore.repository.scheduledevent;


import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScheduledEventRepository extends JpaRepository<ScheduledEvent, Long> {

    Optional<ScheduledEvent> findByScheduledEventId(Long scheduleId);

    @Query("SELECT se FROM ScheduledEvent se WHERE" +
            " :startTime BETWEEN se.timeRange.startTime AND se.timeRange.endTime OR" +
            " :endTime BETWEEN se.timeRange.startTime AND se.timeRange.endTime")
    Optional<List<ScheduledEvent>> findAll(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

}
