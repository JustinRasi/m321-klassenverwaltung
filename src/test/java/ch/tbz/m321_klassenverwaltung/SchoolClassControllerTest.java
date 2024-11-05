package ch.tbz.m321_klassenverwaltung;

import ch.tbz.m321_klassenverwaltung.Controller.SchoolClassController;
import ch.tbz.m321_klassenverwaltung.Model.SchoolClass;
import ch.tbz.m321_klassenverwaltung.Model.User;
import ch.tbz.m321_klassenverwaltung.Repository.SchoolClassRepository;
import ch.tbz.m321_klassenverwaltung.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SchoolClassControllerTest {

    @Mock
    private SchoolClassRepository schoolClassRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SchoolClassController schoolClassController;

    @BeforeEach
    public void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClasses() {
        // Create mock school classes and set up repository to return them
        SchoolClass class1 = new SchoolClass();
        SchoolClass class2 = new SchoolClass();
        when(schoolClassRepository.findAll()).thenReturn(Arrays.asList(class1, class2));

        // Call the method to test and verify the result
        assertEquals(2, schoolClassController.getAllClasses().size());
    }

    @Test
    public void testCreateClass() {
        // Create a mock school class and set up repository to save it
        SchoolClass schoolClass = new SchoolClass();
        when(schoolClassRepository.save(schoolClass)).thenReturn(schoolClass);

        // Call the method to test and verify the result
        assertEquals(schoolClass, schoolClassController.createClass(schoolClass));
    }

    @Test
    public void testGetClassById() {
        // Create a mock school class and set up repository to return it by ID
        SchoolClass schoolClass = new SchoolClass();
        when(schoolClassRepository.findById(1L)).thenReturn(Optional.of(schoolClass));

        // Call the method to test and verify the result
        ResponseEntity<SchoolClass> response = schoolClassController.getClassById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schoolClass, response.getBody());
    }

    @Test
    public void testGetClassByIdNotFound() {
        // Set up repository to return empty optional for a non-existent ID
        when(schoolClassRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method to test and verify the result
        ResponseEntity<SchoolClass> response = schoolClassController.getClassById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteClassById() {
        // Set up repository to return true for existence check and mock deletion
        when(schoolClassRepository.existsById(1L)).thenReturn(true);

        // Call the method to test and verify the result
        ResponseEntity<Void> response = schoolClassController.deleteClassById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(schoolClassRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteClassByIdNotFound() {
        // Set up repository to return false for existence check
        when(schoolClassRepository.existsById(1L)).thenReturn(false);

        // Call the method to test and verify the result
        ResponseEntity<Void> response = schoolClassController.deleteClassById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddUserToClass() {
        // Create mock school class and user, set up repositories to return them by ID
        SchoolClass schoolClass = new SchoolClass();
        User user = new User();
        when(schoolClassRepository.findById(1L)).thenReturn(Optional.of(schoolClass));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the method to test and verify the result
        ResponseEntity<SchoolClass> response = schoolClassController.addUserToClass(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schoolClass, response.getBody());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAddUserToClassNotFound() {
        // Set up repositories to return empty optionals for non-existent IDs
        when(schoolClassRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method to test and verify the result
        ResponseEntity<SchoolClass> response = schoolClassController.addUserToClass(1L, 1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRemoveUserFromClass() {
        // Create mock school class and user, set up repositories to return them by ID
        SchoolClass schoolClass = new SchoolClass();
        User user = new User();
        when(schoolClassRepository.findById(1L)).thenReturn(Optional.of(schoolClass));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the method to test and verify the result
        ResponseEntity<SchoolClass> response = schoolClassController.removeUserFromClass(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRemoveUserFromClassNotFound() {
        // Set up repositories to return empty optionals for non-existent IDs
        when(schoolClassRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method to test and verify the result
        ResponseEntity<SchoolClass> response = schoolClassController.removeUserFromClass(1L, 1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}