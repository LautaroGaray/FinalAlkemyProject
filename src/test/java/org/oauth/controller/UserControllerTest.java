package org.oauth.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.oauth.dto.UserDTO;
import org.oauth.service.IUserService;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private IUserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodas() {
        List<UserDTO> users = Arrays.asList(
                new UserDTO("1", "User1", "lautagaray10@hotmail.com", "myabc123None", null),
                new UserDTO("2", "User2", "user2@example.com", "password2", null)
        );
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<UserDTO>> response = userController.obtenerTodas();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void obtenerPorId() {
        String id = "1";
        UserDTO user = new UserDTO(id, "User1", "lautagaray10@hotmail.com", "myabc123None", null);
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = userController.obtenerPorId(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(id);
    }

    @Test
    void crear() {
        UserDTO user = new UserDTO(null, "User1", "lautagaray10@hotmail.com", "myabc123None", null);
        UserDTO createdUser = new UserDTO("1", "User1", "lautagaray10@hotmail.com", "myabc123None", null);
        when(userService.createUser(user)).thenReturn(createdUser);

        ResponseEntity<UserDTO> response = userController.crear(user);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(createdUser, response.getBody());
        verify(userService, times(1)).createUser(user);
    }

    @Test
    void actualizar() {
        String id = "1";
        UserDTO user = new UserDTO(null, "User1", "lautagaray10@hotmail.com", "myabc123None", null);
        UserDTO updatedUser = new UserDTO(id, "User1", "lautagaray10@hotmail.com", "myabc123NonE", null);
        when(userService.updateUser(id, user)).thenReturn(updatedUser);

        ResponseEntity<UserDTO> response = userController.actualizar(id, user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedUser, response.getBody());
        verify(userService, times(1)).updateUser(id, user);
    }

    @Test
    void eliminar() {
        String id = "1";
        doNothing().when(userService).deleteUser(id);

        ResponseEntity<Void> response = userController.eliminar(id);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUser(id);
    }
}