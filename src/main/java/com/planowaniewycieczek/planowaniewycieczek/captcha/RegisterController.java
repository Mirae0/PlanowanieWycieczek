package com.planowaniewycieczek.planowaniewycieczek.captcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Controller
public class RegisterController {

    @Value("${google.recaptcha.key.secret}")
    private String recaptchaSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @PostMapping("/registerCommit")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("g-recaptcha-response") String recaptchaResponse,
                               Model model) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", recaptchaSecret);
        params.add("response", recaptchaResponse);


        GoogleResponse googleResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_VERIFY_URL, params, GoogleResponse.class);


        if (googleResponse != null && googleResponse.isSuccess()) {
            model.addAttribute("message", "Rejestracja powiodła się!");
            return "registrationSuccess";  // Strona sukcesu rejestracji
        } else {
            model.addAttribute("error", "Błąd weryfikacji reCAPTCHA. Spróbuj ponownie.");
            return "registrationForm";
        }
    }
}
