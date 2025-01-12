package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    private Trip trip;

    @BeforeEach
    public void setUp() {
        // Przygotowanie przykładowych danych
        trip = new Trip("Warszawa", "Zakopane", LocalDate.of(2025, 2, 1));
    }

    @Test
    public void testSaveTrip() {
        // Mockowanie zachowania repozytorium
        when(tripRepository.save(any(Trip.class))).thenReturn(trip);

        // Wywołanie metody serwisowej
        Trip savedTrip = tripService.saveTrip(trip);

        // Sprawdzenie wyników
        assertNotNull(savedTrip);
        assertEquals("Warszawa", savedTrip.getFromLocation());
        assertEquals("Zakopane", savedTrip.getToLocation());
        assertEquals(LocalDate.of(2025, 2, 1), savedTrip.getTripDate());
        verify(tripRepository, times(1)).save(any(Trip.class));
    }

    @Test
    public void testGetAllTrips() {
        // Przygotowanie danych do testu
        Trip trip2 = new Trip("Kraków", "Gdańsk", LocalDate.of(2025, 2, 2));
        when(tripRepository.findAll()).thenReturn(Arrays.asList(trip, trip2));

        // Wywołanie metody serwisowej
        List<Trip> trips = tripService.getAllTrips();

        // Sprawdzenie wyników
        assertNotNull(trips);
        assertEquals(2, trips.size());
        assertEquals("Warszawa", trips.get(0).getFromLocation());
        assertEquals("Kraków", trips.get(1).getFromLocation());
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    public void testGetTripById() {
        // Mockowanie repozytorium, zwrócenie tripa dla id 1L
        when(tripRepository.findById(1L)).thenReturn(Optional.of(trip));

        // Wywołanie metody serwisowej
        Trip foundTrip = tripService.getTripById(1L);

        // Sprawdzenie wyników
        assertNotNull(foundTrip);
        assertEquals("Warszawa", foundTrip.getFromLocation());
        assertEquals("Zakopane", foundTrip.getToLocation());
        assertEquals(LocalDate.of(2025, 2, 1), foundTrip.getTripDate());
        verify(tripRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTripByIdNotFound() {
        // Mockowanie repozytorium, brak tripa dla id 1L
        when(tripRepository.findById(1L)).thenReturn(Optional.empty());

        // Wywołanie metody serwisowej
        Trip foundTrip = tripService.getTripById(1L);

        // Sprawdzenie wyników
        assertNull(foundTrip);
        verify(tripRepository, times(1)).findById(1L);
    }
}
