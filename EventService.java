package com.example.Event.Management.Service;

import com.example.Event.Management.Entity.Event;
import com.example.Event.Management.Entity.RegistrationRequest;

import java.util.List;

/**
 * Service interface for managing events in the event management system.
 */
public interface EventService {

    /**
     * Creates a new event.
     * @param event the event to be created.
     * @return the created event.
     */
    Event createEvent(Event event);

    /**
     * Retrieves all events.
     * @return a list of all events.
     */
    List<Event> getAllEvents();

    /**
     * Retrieves an event by its ID.
     * @param id the ID of the event to retrieve.
     * @return the event with the specified ID, or null if not found.
     */
    Event getEventById(Long id);

    /**
     * Updates an existing event by its ID.
     * @param id the ID of the event to update.
     * @param eventDetails the new details for the event.
     * @return the updated event, or null if the event with the specified ID was not found.
     */
    Event updateEvent(Long id, Event eventDetails);

    /**
     * Deletes an event by its ID.
     * @param id the ID of the event to delete.
     * @return true if the event was successfully deleted, false if the event with the specified ID was not found.
     */
    boolean deleteEvent(Long id);

    /**
     * Registers a user for an event.
     * @param eventId the ID of the event to register the user for.
     * @param request contains the details of the registration request, including the user ID.
     */
    void registerEvent(Long eventId, RegistrationRequest request);

    /**
     * Generates a PDF for the event with the specified ID.
     * @param id the ID of the event to generate the PDF for.
     * @return a byte array containing the PDF data, or null if the event with the specified ID was not found or if an error occurred.
     */
    byte[] generateEventPdf(Long id);
}
