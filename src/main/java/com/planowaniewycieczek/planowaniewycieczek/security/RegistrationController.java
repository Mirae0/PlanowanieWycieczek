package com.planowaniewycieczek.planowaniewycieczek.security;

import com.planowaniewycieczek.planowaniewycieczek.user.UserEntity;
import com.planowaniewycieczek.planowaniewycieczek.user.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(path="/register/user", consumes = "application/x-www-form-urlencoded")
    public void createUser(UserEntity user, HttpServletResponse response) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        response.sendRedirect("/register/success");
    }

    @GetMapping("/register/success")
    public String success() {
        return "redirect: /login";
    }
}
