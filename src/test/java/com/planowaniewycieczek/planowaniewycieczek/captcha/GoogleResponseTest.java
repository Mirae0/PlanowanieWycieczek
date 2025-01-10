package com.planowaniewycieczek.planowaniewycieczek.captcha;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoogleResponseTest {

    @Test
    public void testGettersAndSetters() {
        // Utworzenie instancji GoogleResponse
        GoogleResponse googleResponse = new GoogleResponse();

        // Ustawianie wartości
        googleResponse.setSuccess(true);
        googleResponse.setChallengeTs("2025-01-11T00:00:00Z");
        googleResponse.setHostname("localhost");

        // Sprawdzanie, czy wartości zostały poprawnie ustawione
        assertTrue(googleResponse.isSuccess());
        assertEquals("2025-01-11T00:00:00Z", googleResponse.getChallengeTs());
        assertEquals("localhost", googleResponse.getHostname());
    }

    @Test
    public void testSuccess() {
        // Testowanie success getter i setter
        GoogleResponse googleResponse = new GoogleResponse();
        googleResponse.setSuccess(true);
        assertTrue(googleResponse.isSuccess());

        googleResponse.setSuccess(false);
        assertFalse(googleResponse.isSuccess());
    }

    @Test
    public void testChallengeTs() {
        // Testowanie challengeTs getter i setter
        GoogleResponse googleResponse = new GoogleResponse();
        googleResponse.setChallengeTs("2025-01-11T00:00:00Z");
        assertEquals("2025-01-11T00:00:00Z", googleResponse.getChallengeTs());

        googleResponse.setChallengeTs("2025-01-12T00:00:00Z");
        assertEquals("2025-01-12T00:00:00Z", googleResponse.getChallengeTs());
    }

    @Test
    public void testHostname() {
        // Testowanie hostname getter i setter
        GoogleResponse googleResponse = new GoogleResponse();
        googleResponse.setHostname("localhost");
        assertEquals("localhost", googleResponse.getHostname());

        googleResponse.setHostname("example.com");
        assertEquals("example.com", googleResponse.getHostname());
    }
}
