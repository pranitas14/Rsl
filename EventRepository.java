package com.example.Event.Management.EventRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Event.Management.Entity.Event;

/**
 * Repository interface for accessing and managing Event entities in the database.
 * Provides CRUD operations through the CrudRepository interface.
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    // This interface inherits methods for basic CRUD operations from CrudRepository.
    // Additional query methods can be defined here if needed.

    // Example of a custom query method:
    // List<Event> findByVendorName(String vendorName);
}
