package com.planowaniewycieczek.planowaniewycieczek.trip;

import jakarta.persistence.*;
import java.time.LocalDate;

/*
Poniższy kod określa strukturę tabeli zawierającej informacje o wycieczkach
 */

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

    @Column
    private String photos;

    @Column
    private Long rating;

    @Column
    private Long ratingAmount;

    public Trip() {}

    public Trip(String fromLocation, String toLocation, LocalDate tripDate, String tripNote, String visibility, String photos, Long rating, Long ratingAmount) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.tripDate = tripDate;
        this.tripNote = tripNote;
        this.visibility = visibility;
        this.photos = photos;
        this.rating = rating;
        this.ratingAmount = ratingAmount;
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }


    public Long getRatingAmount() {
        return ratingAmount;
    }

    public void setRatingAmount(Long ratingAmount) {
        this.ratingAmount = ratingAmount;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", tripDate=" + tripDate
                + ", tripNote=" + tripNote + ", visibility=" + visibility + ", photos=" + photos + "] ";
    }
}
