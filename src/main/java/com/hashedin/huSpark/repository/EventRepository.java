package com.hashedin.huSpark.repository;

import com.hashedin.huSpark.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.venue = :venue AND e.endTime > :startTime AND e.startTime < :endTime")
    List<Event> findByVenueAndTimeOverlap(@Param("venue") String venue,
                                          @Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);
}
