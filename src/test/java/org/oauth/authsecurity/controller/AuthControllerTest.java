package org.oauth.authsecurity.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.oauth.authsecurity.dto.AuthRequest;
import org.oauth.authsecurity.dto.AuthResponse;
import org.oauth.authsecurity.service.IAuthService;
import org.oauth.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private IAuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Test User");
        userDTO.setUsername("lautagaray10@hotmail.com");
        userDTO.setPassword("myabc1234None");

        AuthResponse authResponse = new AuthResponse("token123");
        when(authService.register(userDTO)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authController.register(userDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("token123", response.getBody().getToken());
        verify(authService, times(1)).register(userDTO);
    }

    @Test
    void login() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("lautagaray10@hotmail.com");
        authRequest.setPassword("myabc1234None");

        AuthResponse authResponse = new AuthResponse("token123");
        when(authService.authenticate(authRequest)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authController.login(authRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("token123", response.getBody().getToken());
        verify(authService, times(1)).authenticate(authRequest);
    }
}