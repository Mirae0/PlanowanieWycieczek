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

class UserEntityServiceTest {

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
        UserEntity userEntity1 = new UserEntity("JohnDoe", "password1", "john@example.com");
        UserEntity userEntity2 = new UserEntity("JaneDoe", "password2", "jane@example.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(userEntity1, userEntity2));

        // Act
        List<UserEntity> userEntities = userService.getUsers();

        // Assert
        assertEquals(2, userEntities.size());
        assertEquals("JohnDoe", userEntities.get(0).getUsername());
        verify(userRepository, times(1)).findAll(); // Verify that the repository was called
    }

    @Test
    void shouldSaveUser() {
        // Arrange
        UserEntity userEntity = new UserEntity("JohnDoe", "password1", "john@example.com");
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        // Act
        UserEntity savedUserEntity = userService.saveUser(userEntity);

        // Assert
        assertNotNull(savedUserEntity);
        assertEquals("JohnDoe", savedUserEntity.getUsername());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void shouldFindUserByUsername() {
        // Arrange
        String username = "JohnDoe";
        UserEntity userEntity = new UserEntity(username, "password1", "john@example.com");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

        // Act
        UserEntity foundUserEntity = userService.findByUsername(username);

        // Assert
        assertNotNull(foundUserEntity);
        assertEquals(username, foundUserEntity.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void shouldReturnNullIfUserNotFoundByUsername() {
        // Arrange
        String username = "NonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        UserEntity foundUserEntity = userService.findByUsername(username);

        // Assert
        assertNull(foundUserEntity);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void shouldFindUsersByEmail() {
        // Arrange
        String email = "john@example.com";
        UserEntity userEntity = new UserEntity("JohnDoe", "password1", email);
        when(userRepository.findAllByEmail(email)).thenReturn(Arrays.asList(userEntity));

        // Act
        List<UserEntity> userEntities = userService.findUsersByEmail(email);

        // Assert
        assertEquals(1, userEntities.size());
        assertEquals(email, userEntities.get(0).getEmail());
        verify(userRepository, times(1)).findAllByEmail(email);
    }
}
