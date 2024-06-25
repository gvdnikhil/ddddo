package com.hashedin.huSpark.services.Impl;

import com.hashedin.huSpark.exception.event.EventHasBookingsException;
import com.hashedin.huSpark.exception.event.EventNotFoundException;
import com.hashedin.huSpark.exception.event.EventOverlapException;
import com.hashedin.huSpark.model.Event;
import com.hashedin.huSpark.repository.EventRepository;
import com.hashedin.huSpark.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

private final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;


    @Override
    public Event createEvent(Event event) {
        try {
            List<Event> overlappingEvents = eventRepository.findByVenueAndTimeOverlap(
                    event.getVenue(),
                    event.getStartTime(),
                    event.getEndTime()
            );

            if (!overlappingEvents.isEmpty()) {
                throw new EventOverlapException("Event overlaps with existing event at the same venue and time");
            }

            return eventRepository.save(event);
        } catch (EventOverlapException e) {
            // Log the exception
            logger.error("Error creating event: " + e.getMessage());
            throw e; // Re-throw the exception to maintain the original behavior
        }
    }



    @Override
    public List<Event> getAllEvents() {
        logger.info("Getting all events");
        return eventRepository.findAll();
    }

//    @Override
//    public void deleteEvent(Long id) {
//        Event event = eventRepository.findById(id)
//                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
//
//        if (!event.getBookings().isEmpty()) {
//            throw new EventHasBookingsException("Cannot delete event with associated bookings");
//        }
//
//        eventRepository.delete(event);
//    }
}
