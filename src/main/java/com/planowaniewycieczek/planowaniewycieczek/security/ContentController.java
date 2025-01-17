package com.planowaniewycieczek.planowaniewycieczek.security;

import com.planowaniewycieczek.planowaniewycieczek.trip.Trip;
import com.planowaniewycieczek.planowaniewycieczek.user.UserEntity;
import com.planowaniewycieczek.planowaniewycieczek.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ContentController {
    private final UserService userService;

    public ContentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String handleWelcome() {
        return "index";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "user_home";
    }

//    @PostMapping("/znajomi")
//    public String wycieczki(Model model, Optional<String>  name) {
//        if (name.isPresent()) {
//            UserEntity fr = userService.findByUsername(String.valueOf(name));
//            model.addAttribute("friends",fr);
//        }
//        return "znajomi";
//    }
}
