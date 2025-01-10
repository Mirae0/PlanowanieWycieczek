package com.planowaniewycieczek.planowaniewycieczek.trip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TripController.class)
class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddTripSuccess() throws Exception {
        Trip trip = new Trip("Warsaw", "Zielona Góra", LocalDate.of(2024, 12, 12));
        Mockito.when(tripService.saveTrip(any(Trip.class))).thenReturn(trip);

        mockMvc.perform(post("/api/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trip)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fromLocation").value("Warsaw"))
                .andExpect(jsonPath("$.toLocation").value("Zielona Góra"))
                .andExpect(jsonPath("$.tripDate").value("2024-12-12"));
    }

    @Test
    void testAddTripBadRequest() throws Exception {
        Trip trip = new Trip(null, "Zielona Góra", null); // Brak wymaganych pól

        mockMvc.perform(post("/api/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trip)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllTripsSuccess() throws Exception {
        List<Trip> trips = Arrays.asList(
                new Trip("Warsaw", "Zielona Góra", LocalDate.of(2024, 12, 12)),
                new Trip("Gdańsk", "Kraków", LocalDate.of(2024, 11, 10))
        );
        Mockito.when(tripService.getAllTrips()).thenReturn(trips);

        mockMvc.perform(get("/api/trips"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fromLocation").value("Warsaw"))
                .andExpect(jsonPath("$[1].fromLocation").value("Gdańsk"));
    }

    @Test
    void testGetAllTripsNoContent() throws Exception {
        Mockito.when(tripService.getAllTrips()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/trips"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetTripByIdSuccess() throws Exception {
        Trip trip = new Trip("Warsaw", "Zielona Góra", LocalDate.of(2024, 12, 12));
        Mockito.when(tripService.getTripById(anyLong())).thenReturn(trip);

        mockMvc.perform(get("/api/trips/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fromLocation").value("Warsaw"))
                .andExpect(jsonPath("$.toLocation").value("Zielona Góra"));
    }

    @Test
    void testGetTripByIdNotFound() throws Exception {
        Mockito.when(tripService.getTripById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/api/trips/1"))
                .andExpect(status().isNotFound());
    }
}
