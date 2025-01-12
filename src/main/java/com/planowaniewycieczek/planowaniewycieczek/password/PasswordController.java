package com.planowaniewycieczek.planowaniewycieczek.password;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordController {

    @GetMapping("/forgotPassword")
    public String forgotPasswordPage() {
        return "forgotPassword"; // Nazwa widoku Thymeleaf (forgotPassword.html w resources/templates)
    }
}
