package com.example.Event.Management.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

/**
 * Entity representing an event in the Event Management system.
 */
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the event.
     * Must not be blank and should not exceed 100 characters.
     */
    @NotBlank(message = "Event name is mandatory")
    @Size(max = 100, message = "Event name must not exceed 100 characters")
    private String name;

    /**
     * Description of the event.
     * Must not be blank and should not exceed 500 characters.
     */
    @NotBlank(message = "Event description is mandatory")
    @Size(max = 500, message = "Event description must not exceed 500 characters")
    private String description;

    /**
     * Date of the event.
     * Must not be null.
     */
    @NotNull(message = "Event date is mandatory")
    private String date;

    /**
     * Location of the event.
     * Must not be blank and should not exceed 200 characters.
     */
    @NotBlank(message = "Event location is mandatory")
    @Size(max = 200, message = "Event location must not exceed 200 characters")
    private String location;

    /**
     * Time of the event.
     * Must not be null.
     */
    @NotNull(message = "Event time is mandatory")
    private String time;

    /**
     * Users registered for the event.
     * Represented as a many-to-many relationship between Event and User.
     */
    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> registeredUsers;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(Set<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }
}
