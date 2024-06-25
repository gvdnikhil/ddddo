package com.hashedin.huSpark.controller;

import com.hashedin.huSpark.exception.event.EventHasBookingsException;
import com.hashedin.huSpark.exception.event.EventNotFoundException;
import com.hashedin.huSpark.exception.event.EventOverlapException;
import com.hashedin.huSpark.model.Event;
import com.hashedin.huSpark.services.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@Valid @RequestBody Event event) {

            Event createdEvent = eventService.createEvent(event);
            return ResponseEntity.ok(createdEvent);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

  /*  @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok().build();
        } catch (EventNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EventHasBookingsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }*/


}
