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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

import org.slf4j.Logger;

@Controller
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final TripRepository tripRepository;
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    public TripController(TripService tripService, TripRepository tripRepository) {
        this.tripService = tripService;
        this.tripRepository = tripRepository;
    }

    @PostMapping("/dodajwycieczke")
    public String addTrip(@ModelAttribute Trip trip,
                          @RequestParam("photoPaths") MultipartFile[] photos,
                          Model model) {
        try {
            //Jeśli wycieczka nie ma lokacji startowej, końcowej lub daty - wyświetl błąd. Domyślna notatka jest pusta.
            //Domyślna widoczność: publiczna

            if (trip.getFromLocation() == null || trip.getToLocation() == null || trip.getTripDate() == null) {
                return "redirect:/trips/dodajwycieczke?error=true";
            }
            if (trip.getTripNote() == null) {
                trip.setTripNote("");
            }
            if (trip.getVisibility() == null) {
                trip.setVisibility("public");
            }
            if (trip.getRating() == null) {
                trip.setRating(0L);
            }
            if (trip.getRatingAmount() == null) {
                trip.setRatingAmount(0L);
            }
            tripService.saveTrip(trip);

            //Jeśli nie istnieje folder ze zdjęciami zostanie utworzony, jeśli istnieje dla każdego zdjęcia zostanie utworzona ścieżka
            //która zostanie zapisana w bazie

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
                    photoPaths.append("images/").append(fileName).append(",");
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

//    @GetMapping("/ranking")
//    public String ranking(Model model) {
//        List<Trip> trips =
//    }

    @GetMapping("/wycieczki")
    public String getTrips(@RequestParam(defaultValue = "all") String visibility,
                           @RequestParam(defaultValue = "name-asc") String sortBy,
                           Model model) {
        // Pobieranie sortowania na podstawie wartości sortBy
        Sort sort = getSortOrder(sortBy);
        List<Trip> trips;

        // Filtrowanie według widoczności
        switch (visibility) {
            case "public":
                trips = tripRepository.findByVisibility("public", sort);
                break;
            case "private":
                trips = tripRepository.findByVisibility("private", sort);
                break;
            case "friends":
                trips = tripRepository.findByVisibility("friends", sort);
                break;
            default:
                trips = tripRepository.findAll(sort);
        }

        model.addAttribute("trips", trips);
        model.addAttribute("visibility", visibility);
        model.addAttribute("sortBy", sortBy);

        return "wycieczki";
    }

    // Metoda pomocnicza dla sortowania
    private Sort getSortOrder(String sortBy) {
        switch (sortBy) {
            case "name-desc":
                return Sort.by(Sort.Order.desc("fromLocation"));
            case "date-asc":
                return Sort.by(Sort.Order.asc("tripDate"));
            case "date-desc":
                return Sort.by(Sort.Order.desc("tripDate"));
            case "visibility":
                return Sort.by(Sort.Order.asc("visibility"));
            default: // Domyślnie sortowanie po nazwie rosnąco
                return Sort.by(Sort.Order.asc("fromLocation"));
        }
    }

    
}
