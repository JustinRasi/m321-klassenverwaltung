package ch.tbz.m321_klassenverwaltung.Controller;

import ch.tbz.m321_klassenverwaltung.Model.SchoolClass;
import ch.tbz.m321_klassenverwaltung.Model.User;
import ch.tbz.m321_klassenverwaltung.Repository.SchoolClassRepository;
import ch.tbz.m321_klassenverwaltung.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing school classes.
 */
@RestController
@RequestMapping("/classes")
public class SchoolClassController {
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Get all school classes.
     *
     * @return a list of all school classes
     */
    @GetMapping
    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }

    /**
     * Create a new school class.
     *
     * @return the created school class
     */
    @PostMapping("/create")
    public SchoolClass createClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    /**
     * Get a school class by its ID.
     *
     * @param id the ID of the school class
     * @return the school class with the given ID, or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<SchoolClass> getClassById(@PathVariable Long id) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        return schoolClass.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Delete a school class by its ID.
     *
     * @param id the ID of the school class to delete
     * @return a 404 status if the class is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassById(@PathVariable Long id) {
        if (schoolClassRepository.existsById(id)) {
            schoolClassRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Add a user to a school class.
     *
     * @param classId the ID of the school class
     * @param userId the ID of the user to add
     * @return the updated school class, or a 404 status if the class or user is not found
     */
    @PostMapping("/{classId}/addUser/{userId}")
    public ResponseEntity<SchoolClass> addUserToClass(@PathVariable Long classId, @PathVariable Long userId) {
        Optional<SchoolClass> schoolClassOpt = schoolClassRepository.findById(classId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (schoolClassOpt.isPresent() && userOpt.isPresent()) {
            SchoolClass schoolClass = schoolClassOpt.get();
            User user = userOpt.get();
            user.setSchoolClass(schoolClass);
            userRepository.save(user);
            return ResponseEntity.ok(schoolClass);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Remove a user from a school class.
     *
     * @param classId the ID of the school class
     * @param userId the ID of the user to remove
     * @return the updated school class, or a 404 status if the class or user is not found
     */
    @DeleteMapping("/{classId}/removeUser/{userId}")
    public ResponseEntity<SchoolClass> removeUserFromClass(@PathVariable Long classId, @PathVariable Long userId) {
        Optional<SchoolClass> schoolClassOpt = schoolClassRepository.findById(classId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (schoolClassOpt.isPresent() && userOpt.isPresent()) {
            User user = userOpt.get();
            user.setSchoolClass(null);
            userRepository.save(user);
            return ResponseEntity.ok(schoolClassOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}