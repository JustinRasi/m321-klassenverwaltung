package ch.tbz.m321_klassenverwaltung.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity representing a user.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private SchoolClass schoolClass;

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the ID of the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     */
    public void setFullName(String username) {
        this.fullName = username;
    }

    /**
     * Gets the school class associated with the user.
     *
     * @return the school class associated with the user
     */
    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    /**
     * Sets the school class associated with the user.
     *
     * @param schoolClass the school class to associate with the user
     */
    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }
}