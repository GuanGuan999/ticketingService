package com.guanyiping.ticketing.controller;

import com.guanyiping.ticketing.entity.Events;
import com.guanyiping.ticketing.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/getAll")
    public List<Events> getAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public Events getEventById(@PathVariable Long id) {
        return eventService.findEvent(id);
    }

    @PostMapping("/create")
    public Events createEvent(@RequestBody Events event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Events updateEvent(@PathVariable Long id, @RequestBody Events event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }


}
