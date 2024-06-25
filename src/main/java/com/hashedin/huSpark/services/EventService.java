package com.hashedin.huSpark.services;

import com.hashedin.huSpark.model.Event;

import java.util.List;

public interface EventService {

    Event createEvent(Event event);
    List<Event> getAllEvents() ;
   //  void deleteEvent(Long id);
}
