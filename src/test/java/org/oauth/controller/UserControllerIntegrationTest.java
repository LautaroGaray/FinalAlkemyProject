package org.oauth.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void obtenerTodas() {
        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity("/api/v1/users", UserDTO[].class);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void obtenerPorId() {
        String userId = "1";
        ResponseEntity<UserDTO> response = restTemplate.getForEntity("/api/v1/users/" + userId, UserDTO.class);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void crear() {
        UserDTO user = new UserDTO();
        user.setName("Nuevo Usuario");
        user.setUsername("nuevo@example.com");
        user.setPassword("password123");

        ResponseEntity<UserDTO> response = restTemplate.postForEntity("/api/v1/users", user, UserDTO.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Nuevo Usuario", response.getBody().getName());
    }

    @Test
    void actualizar() {
        String userId = "1";
        UserDTO user = new UserDTO();
        user.setName("Usuario Actualizado");
        user.setUsername("actualizado@example.com");
        user.setPassword("newpassword");

        HttpEntity<UserDTO> request = new HttpEntity<>(user);
        ResponseEntity<UserDTO> response = restTemplate.exchange("/api/v1/users/" + userId, HttpMethod.PUT, request, UserDTO.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Usuario Actualizado", response.getBody().getName());
    }

    @Test
    void eliminar() {
        String userId = "1";
        ResponseEntity<Void> response = restTemplate.exchange("/api/v1/users/" + userId, HttpMethod.DELETE, null, Void.class);

        assertEquals(204, response.getStatusCodeValue());
    }
}