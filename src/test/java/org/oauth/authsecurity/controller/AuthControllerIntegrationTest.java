package org.oauth.authsecurity.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oauth.authsecurity.dto.AuthRequest;
import org.oauth.authsecurity.dto.AuthResponse;
import org.oauth.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void register() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Test User");
        userDTO.setUsername("testuser@example.com");
        userDTO.setPassword("password123");

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity("/api/v1/auth/register", userDTO, AuthResponse.class);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void login() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("lautagaray10@hotmail.com");
        authRequest.setPassword("myabc1234None");

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity("/api/v1/auth/login", authRequest, AuthResponse.class);

        assertEquals(200, response.getStatusCodeValue());

    }
}