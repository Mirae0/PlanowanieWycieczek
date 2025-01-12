package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;

@Controller
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/dodajwycieczke")
    public String addTrip(@ModelAttribute Trip trip,
                          @RequestParam("photoPaths") MultipartFile[] photos,
                          Model model) {
        try {

            if (trip.getFromLocation() == null || trip.getToLocation() == null || trip.getTripDate() == null) {
                return "redirect:/trips/dodajwycieczke?error=true";
            }
            if (trip.getTripNote() == null) {
                trip.setTripNote("");
            }

            if (trip.getVisibility() == null) {
                trip.setVisibility("public");
            }
            tripService.saveTrip(trip);
            Path imagesDirectory = Paths.get("src/main/resources/static/images");
            if (!Files.exists(imagesDirectory)) {
                Files.createDirectories(imagesDirectory);
            }
            StringBuilder photoPaths = new StringBuilder();
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String fileName = photo.getOriginalFilename();
                    Path path = Paths.get("src/main/resources/static/images/" + fileName);
                    Files.write(path, photo.getBytes());
                    photoPaths.append("images/").append(fileName).append(", ");
                }
            }
            if (photoPaths.length() > 0) {
                trip.setPhotos(photoPaths.toString());
            } else {
                trip.setPhotos("");
            }
            tripService.saveTrip(trip);
            return "redirect:/wycieczki";
        } catch (IOException e) {
            logger.error("Błąd podczas zapisywania zdjęć: ", e);
            model.addAttribute("error", "Wystąpił błąd podczas zapisywania zdjęć.");
            return "dodajwycieczke";
        }
    }


    @GetMapping("/wycieczki")
    public String getTrips(Model model) {
        List<Trip> trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        return "wycieczki";
    }

}
