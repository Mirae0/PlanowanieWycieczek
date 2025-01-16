package com.planowaniewycieczek.planowaniewycieczek.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository; // Mock UserRepository

    @InjectMocks
    private UserService userService; // Inject the mocked repository into the service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void shouldReturnAllUsers() {
        // Arrange
        User user1 = new User("JohnDoe", "password1", "john@example.com");
        User user2 = new User("JaneDoe", "password2", "jane@example.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<User> users = userService.getUsers();

        // Assert
        assertEquals(2, users.size());
        assertEquals("JohnDoe", users.get(0).getUsername());
        verify(userRepository, times(1)).findAll(); // Verify that the repository was called
    }

    @Test
    void shouldSaveUser() {
        // Arrange
        User user = new User("JohnDoe", "password1", "john@example.com");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("JohnDoe", savedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldFindUserByUsername() {
        // Arrange
        String username = "JohnDoe";
        User user = new User(username, "password1", "john@example.com");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.findByUsername(username);

        // Assert
        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void shouldReturnNullIfUserNotFoundByUsername() {
        // Arrange
        String username = "NonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        User foundUser = userService.findByUsername(username);

        // Assert
        assertNull(foundUser);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void shouldFindUsersByEmail() {
        // Arrange
        String email = "john@example.com";
        User user = new User("JohnDoe", "password1", email);
        when(userRepository.findAllByEmail(email)).thenReturn(Arrays.asList(user));

        // Act
        List<User> users = userService.findUsersByEmail(email);

        // Assert
        assertEquals(1, users.size());
        assertEquals(email, users.get(0).getEmail());
        verify(userRepository, times(1)).findAllByEmail(email);
    }
}
