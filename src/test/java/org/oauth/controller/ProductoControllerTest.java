package org.oauth.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.oauth.dto.ProductoDto;
import org.oauth.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

  @InjectMocks
  private ProductoController productoController;

  @Mock
  private IProductoService productoService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  // ----------- LISTAR TODOS -----------

  @Test
  void listarTodos_DeberiaRetornarListaProductos_OK() throws Exception {
    // Arrange
    List<ProductoDto> productos = Arrays.asList(
        new ProductoDto("1", "Producto1", 100.0, true, new HashSet<>()),
        new ProductoDto("2", "Producto2", 200.0, false, new HashSet<>())
    );
    when(productoService.listarProductos())
        .thenReturn(CompletableFuture.completedFuture(productos));

    // Act
    ResponseEntity<?> response = productoController.listarTodos();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(2, ((List<?>) response.getBody()).size());
    verify(productoService, times(1)).listarProductos();
  }


  // ----------- CREAR PRODUCTO -----------

  @Test
  void crearProducto_DeberiaRetornarProductoCreado_Created() throws Exception {
    // Arrange
    ProductoDto productoInput = new ProductoDto(null, "NuevoProducto", 120.0, true, new HashSet<>());
    ProductoDto productoGuardado = new ProductoDto("123", "NuevoProducto", 120.0, true, new HashSet<>());

    when(productoService.crearProducto(productoInput)).thenReturn(productoGuardado);

    // Act
    ResponseEntity<?> response = productoController.crearProducto(productoInput);

    // Assert
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("123", ((ProductoDto) response.getBody()).getId());
    verify(productoService).crearProducto(productoInput);
  }


  // ----------- ACTUALIZAR PRODUCTO -----------

  @Test
  void actualizarProducto_DeberiaActualizarYRetornar_OK() throws Exception {
    // Arrange
    String id = "abc";
    ProductoDto updateDto = new ProductoDto(null, "Editado", 150.0, false, new HashSet<>());
    ProductoDto actualizado = new ProductoDto(id, "Editado", 150.0, false, new HashSet<>());

    when(productoService.actualizarProducto(id, updateDto)).thenReturn(actualizado);

    // Act
    ResponseEntity<?> response = productoController.actualizarProducto(id, updateDto);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("abc", ((ProductoDto) response.getBody()).getId());
    verify(productoService).actualizarProducto(id, updateDto);
  }

  // ----------- ELIMINAR PRODUCTO -----------

  @Test
  void eliminarProducto_DeberiaEliminarYRetornar_NoContent() {
    // Arrange
    String id = "abc";
    doNothing().when(productoService).eliminarProducto(id);

    // Act
    ResponseEntity<?> response = productoController.eliminarProducto(id);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(productoService).eliminarProducto(id);
  }


}
