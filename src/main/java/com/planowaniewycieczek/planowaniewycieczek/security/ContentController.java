package com.planowaniewycieczek.planowaniewycieczek.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/home")
    public String handleHome() {
        return "wycieczki";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "user_home";
    }
}
