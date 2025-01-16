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
public class UserEntityRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity1;
    private UserEntity userEntity2;

    @BeforeEach
    public void setUp() {
        // Przygotowanie danych testowych
        userEntity1 = new UserEntity("john_doe", "password123", "john.doe@example.com");
        userEntity2 = new UserEntity("jane_doe", "password123", "jane.doe@example.com");
    }

    @Test
    public void testSaveAndFindByUsername() {
        // Zapisanie użytkowników
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        // Sprawdzenie, czy użytkownik został zapisany
        Optional<UserEntity> foundUser = userRepository.findByUsername(userEntity1.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals(userEntity1.getUsername(), foundUser.get().getUsername());
    }

    @Test
    public void testFindByEmail() {
        // Zapisanie użytkownika
        userRepository.save(userEntity1);

        // Wyszukiwanie użytkownika po e-mailu
        List<UserEntity> usersByEmail = userRepository.findAllByEmail(userEntity1.getEmail());
        assertFalse(usersByEmail.isEmpty());
        assertEquals(1, usersByEmail.size());
        assertEquals(userEntity1.getEmail(), usersByEmail.get(0).getEmail());
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
