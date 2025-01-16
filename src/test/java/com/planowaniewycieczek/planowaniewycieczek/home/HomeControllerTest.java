package com.planowaniewycieczek.planowaniewycieczek.home;

import com.planowaniewycieczek.planowaniewycieczek.trip.TripService;
import com.planowaniewycieczek.planowaniewycieczek.user.UserEntity;
import com.planowaniewycieczek.planowaniewycieczek.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private TripService tripService;

    @InjectMocks
    private HomeController homeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testWycieczki() throws Exception {
        // Przygotowanie danych dla tripów
        when(tripService.getAllTrips()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/wycieczki"))
                .andExpect(status().isOk())
                .andExpect(view().name("wycieczki"))
                .andExpect(model().attributeExists("wyc"));

        verify(tripService, times(1)).getAllTrips();
    }

    @Test
    public void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testLoginSubmitSuccess() throws Exception {
        // Przygotowanie danych testowych
        UserEntity testUserEntity = new UserEntity("john_doe", "password123", "john.doe@example.com");
        when(userService.findByUsername("john_doe")).thenReturn(testUserEntity);

        mockMvc.perform(post("/login")
                        .param("username", "john_doe")
                        .param("password", "password123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/hello"))
                .andExpect(model().attribute("Uname", "john_doe"));

        verify(userService, times(1)).findByUsername("john_doe");
    }

    @Test
    public void testLoginSubmitFailure() throws Exception {
        // Przygotowanie danych testowych
        UserEntity testUserEntity = new UserEntity("john_doe", "password123", "john.doe@example.com");
        when(userService.findByUsername("john_doe")).thenReturn(testUserEntity);

        mockMvc.perform(post("/login")
                        .param("username", "john_doe")
                        .param("password", "wrong_password"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", "Nieprawidłowa nazwa użytkownika lub hasło."));

        verify(userService, times(1)).findByUsername("john_doe");
    }

    @Test
    public void testShowRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        UserEntity testUserEntity = new UserEntity("john_doe", "password123", "john.doe@example.com");
        when(userService.saveUser(testUserEntity)).thenReturn(testUserEntity);

        mockMvc.perform(post("/register")
                        .param("username", "john_doe")
                        .param("password", "password123")
                        .param("email", "john.doe@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(userService, times(1)).saveUser(testUserEntity);
    }
}
