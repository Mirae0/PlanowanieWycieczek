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
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();

        user1.setUsername("username1");
        user1.setPassword("$2a$12$lg6hWMkoe2mjNmast6Uh8ujyzDf5jVJZHQYeML4omvydXrDPyMUoS");
        user1.setEmail("email1@gmail.com");

        user2.setUsername("username2");
        user2.setPassword("$2a$12$taPDJxViR2/x8jgjswKmGeEs/2x.8AUZ2KdJC2kNHGhPNq7JR3bk2");
        user2.setEmail("email2@gmail.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UserEntity> users = userService.getUsers();

        // Assert
        assertEquals(2, users.size());
        assertEquals("username1", users.get(0).getUsername());
        verify(userRepository, times(1)).findAll(); // Verify that the repository was called
    }

    @Test
    void shouldSaveUser() {
        // Arrange
        UserEntity user1 = new UserEntity();

        user1.setUsername("username1");
        user1.setPassword("$2a$12$lg6hWMkoe2mjNmast6Uh8ujyzDf5jVJZHQYeML4omvydXrDPyMUoS");
        user1.setEmail("email1@gmail.com");
        when(userRepository.save(user1)).thenReturn(user1);

        // Act
        UserEntity savedUser = userService.saveUser(user1);

        // Assert
        assertNotNull(savedUser);
        assertEquals("JohnDoe", savedUser.getUsername());
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void shouldFindUserByUsername() {
        // Arrange
        String username = "JohnDoe";
        UserEntity user = new UserEntity();

        user.setUsername("JohnDoe");
        user.setPassword("$2a$12$lg6hWMkoe2mjNmast6Uh8ujyzDf5jVJZHQYeML4omvydXrDPyMUoS");
        user.setEmail("email1@gmail.com");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        UserEntity foundUser = userService.findByUsername(username);

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
        UserEntity foundUser = userService.findByUsername(username);

        // Assert
        assertNull(foundUser);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void shouldFindUsersByEmail() {
        // Arrange
        String email = "john@example.com";
        UserEntity user = new UserEntity();

        user.setUsername("JohnDoe");
        user.setPassword("$2a$12$lg6hWMkoe2mjNmast6Uh8ujyzDf5jVJZHQYeML4omvydXrDPyMUoS");
        user.setEmail(email);
        when(userRepository.findAllByEmail(email)).thenReturn(Arrays.asList(user));

        // Act
        List<UserEntity> users = userService.findUsersByEmail(email);

        // Assert
        assertEquals(1, users.size());
        assertEquals(email, users.get(0).getEmail());
        verify(userRepository, times(1)).findAllByEmail(email);
    }
}
