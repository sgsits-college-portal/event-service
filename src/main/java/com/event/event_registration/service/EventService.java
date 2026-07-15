package com.event.event_registration.service;

import com.event.event_registration.entity.Event;
import com.event.event_registration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Page<Event> getAllEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepository.findAll(pageable);
    }

    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}