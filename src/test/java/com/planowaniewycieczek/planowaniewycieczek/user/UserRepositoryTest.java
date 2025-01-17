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

    private UserEntity user1;
    private UserEntity user2;

    @BeforeEach
    public void setUp() {
        // Przygotowanie danych testowych
        user1.setUsername("username1");
        user1.setPassword("");
        user1.setEmail("email1");

        user2.setUsername("username2");
        user2.setPassword("");
        user2.setEmail("email2");
    }

    @Test
    public void testSaveAndFindByUsername() {
        // Zapisanie użytkowników
        userRepository.save(user1);
        userRepository.save(user2);

        // Sprawdzenie, czy użytkownik został zapisany
        Optional<UserEntity> foundUser = userRepository.findByUsername(user1.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals(user1.getUsername(), foundUser.get().getUsername());
    }

    @Test
    public void testFindByEmail() {
        // Zapisanie użytkownika
        userRepository.save(user1);

        // Wyszukiwanie użytkownika po e-mailu
        List<UserEntity> usersByEmail = userRepository.findAllByEmail(user1.getEmail());
        assertFalse(usersByEmail.isEmpty());
        assertEquals(1, usersByEmail.size());
        assertEquals(user1.getEmail(), usersByEmail.get(0).getEmail());
    }

    @Test
    public void testFindByEmailNotFound() {
        // Wyszukiwanie użytkownika, który nie istnieje
        List<UserEntity> usersByEmail = userRepository.findAllByEmail("nonexistent@example.com");
        assertTrue(usersByEmail.isEmpty());
    }

    @Test
    public void testFindByUsernameNotFound() {
        // Wyszukiwanie użytkownika po nieistniejącym username
        Optional<UserEntity> foundUser = userRepository.findByUsername("nonexistent_user");
        assertFalse(foundUser.isPresent());
    }
}
