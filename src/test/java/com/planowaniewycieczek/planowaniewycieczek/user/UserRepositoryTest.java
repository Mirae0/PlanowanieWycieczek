package com.planowaniewycieczek.planowaniewycieczek.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        // Przygotowanie danych testowych
        user1 = new User("john_doe", "password123", "john.doe@example.com");
        user2 = new User("jane_doe", "password123", "jane.doe@example.com");
    }

    @Test
    public void testSaveAndFindByUsername() {
        // Zapisanie użytkowników
        userRepository.save(user1);
        userRepository.save(user2);

        // Sprawdzenie, czy użytkownik został zapisany
        Optional<User> foundUser = userRepository.findByUsername(user1.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals(user1.getUsername(), foundUser.get().getUsername());
    }

    @Test
    public void testFindByEmail() {
        // Zapisanie użytkownika
        userRepository.save(user1);

        // Wyszukiwanie użytkownika po e-mailu
        List<User> usersByEmail = userRepository.findAllByEmail(user1.getEmail());
        assertFalse(usersByEmail.isEmpty());
        assertEquals(1, usersByEmail.size());
        assertEquals(user1.getEmail(), usersByEmail.get(0).getEmail());
    }

    @Test
    public void testFindByEmailNotFound() {
        // Wyszukiwanie użytkownika, który nie istnieje
        List<User> usersByEmail = userRepository.findAllByEmail("nonexistent@example.com");
        assertTrue(usersByEmail.isEmpty());
    }

    @Test
    public void testFindByUsernameNotFound() {
        // Wyszukiwanie użytkownika po nieistniejącym username
        Optional<User> foundUser = userRepository.findByUsername("nonexistent_user");
        assertFalse(foundUser.isPresent());
    }
}
