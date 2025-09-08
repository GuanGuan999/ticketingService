package com.guanyiping.ticketing.service;

import com.guanyiping.ticketing.entity.Events;
import com.guanyiping.ticketing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Events createEvent(Events event) {
        return eventRepository.save(event);
    }

    public Events updateEvent(Long id, Events updateEvent) {
        Events event = this.findEvent(id);
        event.setTitle(updateEvent.getTitle());
        event.setDescription(updateEvent.getDescription());
        event.setLocation(updateEvent.getLocation());
        event.setEventDate(updateEvent.getEventDate());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Events findEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Events> findAllEvents() {
        return eventRepository.findAll();
    }

}
