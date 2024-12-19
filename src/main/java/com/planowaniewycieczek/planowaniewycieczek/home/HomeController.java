package com.planowaniewycieczek.planowaniewycieczek.home;

import com.planowaniewycieczek.planowaniewycieczek.user.User;
import com.planowaniewycieczek.planowaniewycieczek.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/wycieczki")
    public String wycieczki(Model model) {
        model.addAttribute("testowka","TESTOWY THYMELEAF");
        return "wycieczki";
    }

    @GetMapping("/znajomi")
    public String znajomi(Model model) {
        return "znajomi";
    }

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
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
