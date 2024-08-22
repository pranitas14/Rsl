package com.example.Event.Management.EventRepository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Event.Management.Entity.User;

import java.util.Optional;

/**
 * Repository interface for accessing and managing User entities in the database.
 * Extends CrudRepository to provide CRUD operations.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // Additional custom query methods can be defined here if needed.
    Optional<User> findByEmail(String email);
}
