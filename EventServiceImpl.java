package com.example.Event.Management.EventServiceImpl;


import com.example.Event.Management.Entity.Event;
import com.example.Event.Management.Entity.RegistrationRequest;
import com.example.Event.Management.Entity.User;
import com.example.Event.Management.EventRepository.EventRepository;
import com.example.Event.Management.EventRepository.UserRepository;
import com.example.Event.Management.Service.EventService;
import com.example.Event.Management.Service.PdfService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the EventService interface.
 * Provides business logic for managing events, including CRUD operations and PDF generation.
 */
@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all events from the repository.
     * @return a list of all events.
     */
    @Override
    public List<Event> getAllEvents() {
        Iterable<Event> eventsIterable = eventRepository.findAll();
        List<Event> events = new ArrayList<>();
        eventsIterable.forEach(events::add);
        logger.info("Retrieved all events");
        return events;
    }

    /**
     * Retrieves an event by its ID.
     * @param id the ID of the event.
     * @return the event if found, or null if not found.
     */
    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .map(event -> {
                    logger.info("Retrieved event with id {}", id);
                    return event;
                })
                .orElseGet(() -> {
                    logger.warn("Event with id {} not found", id);
                    return null;
                });
    }

    /**
     * Creates a new event in the repository.
     * @param event the event to create.
     * @return the created event.
     */
    @Override
    public Event createEvent(Event event) {
        Event createdEvent = eventRepository.save(event);
        logger.info("Created new event with id {}", createdEvent.getId());
        return createdEvent;
    }

    /**
     * Updates an existing event identified by its ID.
     * @param id the ID of the event to update.
     * @param eventDetails the new details of the event.
     * @return the updated event if found and updated, or null if the event was not found.
     */
    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setName(eventDetails.getName());
                    event.setDescription(eventDetails.getDescription());
                    event.setDate(eventDetails.getDate());
                    event.setLocation(eventDetails.getLocation());
                    event.setTime(eventDetails.getTime());
                    Event updatedEvent = eventRepository.save(event);
                    logger.info("Updated event with id {}", updatedEvent.getId());
                    return updatedEvent;
                })
                .orElseGet(() -> {
                    logger.warn("Event with id {} not found for update", id);
                    return null;
                });
    }

    /**
     * Deletes an event by its ID.
     * @param id the ID of the event to delete.
     * @return true if the event was deleted, or false if the event was not found.
     */
    @Override
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            logger.info("Deleted event with id {}", id);
            return true;
        } else {
            logger.warn("Event with id {} not found for deletion", id);
            return false;
        }
    }

    /**
     * Registers a user for an event.
     * @param eventId the ID of the event to register for.
     * @param request the registration request containing the user ID.
     */
    @Override
    public void registerEvent(Long eventId, RegistrationRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    logger.warn("Event with id {} not found", eventId);
                    return new RuntimeException("Event not found");
                });

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> {
                    logger.warn("User with id {} not found", request.getUserId());
                    return new RuntimeException("User not found");
                });

        event.getRegisteredUsers().add(user);
        eventRepository.save(event);

        logger.info("User with id {} registered for event with id {}", request.getUserId(), eventId);
    }

    /**
     * Generates a PDF document for an event.
     * @param id the ID of the event to generate a PDF for.
     * @return the PDF document as a byte array, or null if the event was not found or an error occurred.
     */
    @Override
    public byte[] generateEventPdf(Long id) {
        return eventRepository.findById(id)
                .map(event -> {
                    try {
                        byte[] pdfBytes = PdfService.createEventPdf(event);
                        logger.info("PDF generated for event with id {}", id);
                        return pdfBytes;
                    } catch (Exception e) {
                        logger.error("Error generating PDF for event with id {}", id, e);
                        return null;
                    }
                })
                .orElseGet(() -> {
                    logger.warn("Event with id {} not found for PDF generation", id);
                    return null;
                });
    }
}
