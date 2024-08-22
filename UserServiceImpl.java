package com.example.Event.Management.EventServiceImpl;




import com.example.Event.Management.Entity.User;
import com.example.Event.Management.EventRepository.UserRepository;
import com.example.Event.Management.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the UserService interface.
 * Provides business logic for managing users, including CRUD operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user and saves it to the repository.
     *
     * @param user the user to be created
     * @return the created user
     */
    @Override
    public User createUser(User user) {
        if (user == null) {
            logger.warn("Attempted to create a null user");
            throw new IllegalArgumentException("User must not be null");
        }
        User createdUser = userRepository.save(user);
        logger.info("Created new user with ID: {}", createdUser.getId());
        return createdUser;
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll(); // Corrected
        List<User> users = new ArrayList<>();
        usersIterable.forEach(users::add);
        logger.info("Retrieved all users");
        return users;
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user
     * @return the user if found, or null if not found
     */
    @Override
    public User getUserById(Long id) {
        if (id == null) {
            logger.warn("Attempted to retrieve user with a null ID");
            throw new IllegalArgumentException("ID must not be null");
        }
        return userRepository.findById(id)
                .orElseGet(() -> {
                    logger.warn("User with ID {} not found", id);
                    return null;
                });
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param user the updated user details
     * @return the updated user, or null if the user does not exist
     */
    @Override
    public User updateUser(Long id, User user) {
        if (id == null || user == null) {
            logger.warn("Attempted to update with null ID or user");
            throw new IllegalArgumentException("ID and user must not be null");
        }
        if (!userRepository.existsById(id)) {
            logger.warn("User with ID {} does not exist", id);
            return null;
        }
        user.setId(id);
        User updatedUser = userRepository.save(user);
        logger.info("Updated user with ID: {}", id);
        return updatedUser;
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was deleted, false if the user does not exist
     */
    @Override
    public boolean deleteUser(Long id) {
        if (id == null) {
            logger.warn("Attempted to delete a user with a null ID");
            throw new IllegalArgumentException("ID must not be null");
        }
        if (!userRepository.existsById(id)) {
            logger.warn("User with ID {} does not exist", id);
            return false;
        }
        userRepository.deleteById(id);
        logger.info("Deleted user with ID: {}", id);
        return true;
    }
}
