package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void saveTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

//    public List<Trip> getTopTen() {
//        List<Trip> all = getAllTrips();
//        for(Trip trip : all) {
//        }
//    }
}
