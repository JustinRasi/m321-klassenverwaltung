package ch.tbz.m321_klassenverwaltung.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a school class.
 */
@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private String description;

    @OneToMany(mappedBy = "schoolClass")
    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    /**
     * Gets the ID of the school class.
     *
     * @return the ID of the school class
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the school class.
     *
     * @param id the ID of the school class
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the school class.
     *
     * @return the name of the school class
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the name of the school class.
     *
     * @param className the name of the school class
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets the description of the school class.
     *
     * @return the description of the school class
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the school class.
     *
     * @param description the description of the school class
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the users associated with the school class.
     *
     * @return a set of users associated with the school class
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Sets the users associated with the school class.
     *
     * @param users a set of users to associate with the school class
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}