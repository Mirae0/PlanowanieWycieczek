package com.planowaniewycieczek.planowaniewycieczek.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        // Przykładowe dane użytkowników
        user1 = new User("john_doe", "password123", "john@example.com");
        user2 = new User("jane_doe", "password456", "jane@example.com");
    }

    @Test
    void testGetUsers() throws Exception {
        // Mockowanie serwisu
        List<UserEntity> users = Arrays.asList(user1, user2);
        when(userService.getUsers()).thenReturn(users);

        // Testowanie endpointu GET /api/user
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("john_doe"))
                .andExpect(jsonPath("$[1].username").value("jane_doe"));
    }

    @Test
    void testAddUser() throws Exception {
        // Mockowanie serwisu
        when(userService.saveUser(Mockito.any(User.class))).thenReturn(user1);

        // Testowanie endpointu POST /api/user
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testSearchUserByEmail() throws Exception {
        // Mockowanie serwisu
        List<User> users = Arrays.asList(user1);
        when(userService.findUsersByEmail("john@example.com")).thenReturn(users);

        // Testowanie endpointu POST /api/user/searchUserByEmail
        mockMvc.perform(post("/api/user/searchUserByEmail")
                        .param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].email").value("john@example.com"));
    }
}
