package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @NonNull
    List<Trip> findAll(@NonNull Sort sort);

    @NonNull
    List<Trip> findByVisibility(@NonNull String visibility, @NonNull Sort sort);
}
