package com.planowaniewycieczek.planowaniewycieczek.captcha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RestController
public class LoginController {

    @Value("${google.recaptcha.key.secret}")
    private String recaptchaSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @PostMapping("/login")
    public String login(@RequestParam("email") String name,
                        @RequestParam("password") String password,
                        @RequestParam("g-recaptcha-response") String recaptchaResponse) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", recaptchaSecret);
        params.add("response", recaptchaResponse);
        GoogleResponse googleResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_VERIFY_URL, params, GoogleResponse.class);

        if (googleResponse != null && googleResponse.isSuccess()) {
            return "user_home";
        } else {
            return "login";
        }
    }
}
