package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TripRepositoryTest {

    @Autowired
    private TripRepository tripRepository;

    private Trip trip1;
    private Trip trip2;

    @BeforeEach
    public void setUp() {
        trip1 = new Trip("Warszawa", "Zakopane", LocalDate.of(2025, 2, 1),"","public","",null,null);
        trip2 = new Trip("Kraków", "Gdańsk", LocalDate.of(2025, 2, 2),"","public","",null,null);
    }

    @Test
    public void testSaveTrip() {
        tripRepository.save(trip1);
        tripRepository.save(trip2);

        Optional<Trip> savedTrip1 = tripRepository.findById(trip1.getId());
        Optional<Trip> savedTrip2 = tripRepository.findById(trip2.getId());

        assertTrue(savedTrip1.isPresent());
        assertTrue(savedTrip2.isPresent());

        assertEquals("Warszawa", savedTrip1.get().getFromLocation());
        assertEquals("Zakopane", savedTrip1.get().getToLocation());
        assertEquals(LocalDate.of(2025, 2, 1), savedTrip1.get().getTripDate());

        assertEquals("Kraków", savedTrip2.get().getFromLocation());
        assertEquals("Gdańsk", savedTrip2.get().getToLocation());
        assertEquals(LocalDate.of(2025, 2, 2), savedTrip2.get().getTripDate());
    }

    @Test
    public void testFindAllTrips() {
        tripRepository.flush();

        List<Trip> trips = tripRepository.findAll();

        trips.forEach(trip -> System.out.println(trip));

        assertNotNull(trips);
        assertEquals(2, trips.size());
    }



    @Test
    public void testFindById() {
        tripRepository.save(trip1);

        Optional<Trip> foundTrip = tripRepository.findById(trip1.getId());

        assertTrue(foundTrip.isPresent());
        assertEquals("Warszawa", foundTrip.get().getFromLocation());
        assertEquals("Zakopane", foundTrip.get().getToLocation());
        assertEquals(LocalDate.of(2025, 2, 1), foundTrip.get().getTripDate());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<Trip> foundTrip = tripRepository.findById(999L);

        assertFalse(foundTrip.isPresent());
    }

    @Test
    public void testDeleteTrip() {
        tripRepository.save(trip1);

        tripRepository.deleteById(trip1.getId());

        Optional<Trip> deletedTrip = tripRepository.findById(trip1.getId());

        assertFalse(deletedTrip.isPresent());
    }
}
