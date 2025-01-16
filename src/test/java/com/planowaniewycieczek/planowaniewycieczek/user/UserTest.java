package com.planowaniewycieczek.planowaniewycieczek.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructor() {
        User user = new User("JohnDoe", "password123", "john@example.com");

        assertEquals("JohnDoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testSettersAndGetters() {
        User user = new User();

        user.setId(1L);
        user.setUsername("JaneDoe");
        user.setPassword("securePass");
        user.setEmail("jane@example.com");

        assertEquals(1L, user.getId());
        assertEquals("JaneDoe", user.getUsername());
        assertEquals("securePass", user.getPassword());
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    void testToString() {
        User user = new User("JohnDoe", "password123", "john@example.com");

        String expected = "User [id=null, username=JohnDoe, email=john@example.com]";
        assertEquals(expected, user.toString());
    }
}