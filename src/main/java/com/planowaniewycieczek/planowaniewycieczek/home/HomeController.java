package com.planowaniewycieczek.planowaniewycieczek.home;

import com.planowaniewycieczek.planowaniewycieczek.trip.Trip;
import com.planowaniewycieczek.planowaniewycieczek.trip.TripService;
import com.planowaniewycieczek.planowaniewycieczek.user.User;
import com.planowaniewycieczek.planowaniewycieczek.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final TripService tripService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/index")
    public String indexI(Model model) {
        return "index";
    }

    @GetMapping("/dodajwycieczke")
    public String dodajwycieczke(){
        return "dodajwycieczke";
    }

    @GetMapping("/ranking")
    public String ranking(){
        return "ranking";
    }

    @GetMapping("/wycieczki")
    public String wycieczki(Model model) {
//        model.addAttribute("testowka","TESTOWY THYMELEAF");
        List<Trip> trips = tripService.getAllTrips();
        model.addAttribute("trips",trips);
        return "wycieczki";
    }

    @GetMapping("/znajomi")
    public String znajomi(Model model) {
        return "znajomi";
    }

    @Autowired
    public HomeController(UserService userService, TripService tripService) {
        this.userService = userService;
        this.tripService = tripService;
    }
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("Uname", user.getUsername());
            return "hello";
        }
        model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło.");
        return "login";
    }
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        return "redirect:/login";
    }
}
