package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    @Test
    void testTripConstructor() {
        // Arrange
        String fromLocation = "Warsaw";
        String toLocation = "Zielona Góra";
        LocalDate date = LocalDate.of(2024, 12, 12);

        // Act
        Trip trip = new Trip(fromLocation, toLocation, date,"","public","",null,null);

        // Assert
        assertNotNull(trip); // Verify the trip object is created
        assertEquals(fromLocation, trip.getFromLocation());
        assertEquals(toLocation, trip.getToLocation());
        assertEquals(date, trip.getTripDate());
    }

    @Test
    void testSettersAndGetters(){
        Trip trip = new Trip();
        LocalDate date = LocalDate.of(2024, 12, 12);

        trip.setId(1L);
        trip.setTripDate(date);
        trip.setFromLocation("Warsaw");
        trip.setToLocation("Zielona Góra");

        assertEquals(1L, trip.getId());
        assertEquals(date, trip.getTripDate());
        assertEquals("Warsaw", trip.getFromLocation());
        assertEquals("Zielona Góra", trip.getToLocation());
    }

    @Test
    void testToString(){
        LocalDate date = LocalDate.of(2024, 12, 12);
        Trip trip = new Trip("Warsaw", "Zielona Góra", date,"","public","",null,null);
        trip.setId(1L);

        String expected = "Trip [id=1, fromLocation= Warsaw, toLocation=Zielona Góra, tripDate=2024-12-12]";
        assertEquals(expected, trip.toString());
    }

}