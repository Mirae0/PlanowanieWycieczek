package com.planowaniewycieczek.planowaniewycieczek.trip;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromLocation;

    @Column(nullable = false)
    private String toLocation;

    @Column(nullable = false)
    private LocalDate tripDate;

    public Trip() {}

    public Trip(String fromLocation, String toLocation, LocalDate tripDate) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.tripDate = tripDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", tripDate=" + tripDate + "]";
    }
}
