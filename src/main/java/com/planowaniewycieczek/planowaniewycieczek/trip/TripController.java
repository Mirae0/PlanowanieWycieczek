package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    //Poniższy kod umożliwia zapisywanie wycieczek do bazy danych przy wykorzystaniu formularza i metody POST
    @PostMapping
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        if (trip.getFromLocation() == null || trip.getToLocation() == null || trip.getTripDate() == null) {
            return ResponseEntity.badRequest().build();
        }

        Trip savedTrip = tripService.saveTrip(trip);
        return ResponseEntity.ok(savedTrip);
    }

    //Przy żądaniu GET poniższy kod zwraca wszystkie wycieczki zapisane w bazie danych
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        if (trips.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trips);
    }

    //Poniższy kod umożliwia zwrócenie konkretnej zapisanej wycieczki wybieranej na podstawie jej id
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip = tripService.getTripById(id);
        if (trip == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trip);
    }

}
