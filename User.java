package com.example.Event.Management.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Entity representing a user in the Event Management system.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the user.
     * Must not be blank.
     */
    @NotBlank(message = "User name is mandatory")
    private String name;

    /**
     * Email of the user.
     * Must be a valid email format and not blank.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * Events the user is registered for.
     * Represented as a many-to-many relationship.
     */
    @ManyToMany(mappedBy = "registeredUsers")
    private Set<Event> events;

    /**
     * Vendors associated with the user.
     * Represented as a one-to-many relationship.
     */
    @OneToMany(mappedBy = "user")
    private Set<Vendor> vendors;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(Set<Vendor> vendors) {
        this.vendors = vendors;
    }
}
