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

    private String tripNote;
    private String visibility;

    @Column(name = "photos")
    private String photos;


    public Trip() {}

    public Trip(String fromLocation, String toLocation, LocalDate tripDate, String tripNote, String visibility, String photos) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.tripDate = tripDate;
        this.tripNote = tripNote;
        this.visibility = visibility;
        this.photos = photos;
    }

    // Getters and setters

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

    public String getTripNote() {
        return tripNote;
    }

    public void setTripNote(String tripNote) {
        this.tripNote = tripNote;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", tripDate=" + tripDate
                + ", tripNote=" + tripNote + ", visibility=" + visibility + ", photos=" + photos + "]";
    }
}
