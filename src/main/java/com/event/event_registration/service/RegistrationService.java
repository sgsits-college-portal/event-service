package com.event.event_registration.service;

import com.event.event_registration.entity.Event;
import com.event.event_registration.entity.Registration;
import com.event.event_registration.repository.EventRepository;
import com.event.event_registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    // CREATE (for controller)
    public Registration register(Registration registration) {
        return registrationRepository.save(registration);
    }
    @Autowired
    private EventRepository eventRepository;

    public Registration register(Registration registration, Integer eventId, Integer userId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        long currentRegistrations = registrationRepository.countByEvent_EventId(eventId);
        if (currentRegistrations >= event.getCapacity()) {
            throw new RuntimeException("Event is at full capacity!");
        }

        registration.setEvent(event);
        registration.setParticipantUserId(userId);

        return registrationRepository.save(registration);
    }
    // GET ALL
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // GET BY ID
    public Optional<Registration> getRegistrationById(Integer id) {
        return registrationRepository.findById(id);
    }

    // DELETE
    public void deleteRegistration(Integer id) {
        registrationRepository.deleteById(id);
    }


    public List<Registration> getByEventId(Integer eventId) {
        return registrationRepository.findByEvent_EventId(eventId);
    }

}