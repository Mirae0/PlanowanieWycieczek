package com.planowaniewycieczek.planowaniewycieczek.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void testUserConstructor() {
        UserEntity userEntity = new UserEntity("JohnDoe", "password123", "john@example.com");

        assertEquals("JohnDoe", userEntity.getUsername());
        assertEquals("password123", userEntity.getPassword());
        assertEquals("john@example.com", userEntity.getEmail());
    }

    @Test
    void testSettersAndGetters() {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setUsername("JaneDoe");
        userEntity.setPassword("securePass");
        userEntity.setEmail("jane@example.com");

        assertEquals(1L, userEntity.getId());
        assertEquals("JaneDoe", userEntity.getUsername());
        assertEquals("securePass", userEntity.getPassword());
        assertEquals("jane@example.com", userEntity.getEmail());
    }

    @Test
    void testToString() {
        UserEntity userEntity = new UserEntity("JohnDoe", "password123", "john@example.com");

        String expected = "User [id=null, username=JohnDoe, email=john@example.com]";
        assertEquals(expected, userEntity.toString());
    }
}