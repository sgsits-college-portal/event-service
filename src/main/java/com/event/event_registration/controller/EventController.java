package com.event.event_registration.controller;

import com.event.event_registration.entity.Event;
import com.event.event_registration.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // CREATE EVENT (Only ADMIN can create)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = {"", "/"})
    public Event createEvent(@Valid @RequestBody Event event) {

        System.out.println("Event Name = " + event.getEventName());
        System.out.println("Capacity = " + event.getCapacity());
        System.out.println("Venue = " + event.getVenue());

        return eventService.saveEvent(event);
    }

    @GetMapping
    public Page<Event> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return eventService.getAllEvents(page, size);
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Integer id) {
        return eventService.getEventById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return "Event Deleted Successfully";
    }
}