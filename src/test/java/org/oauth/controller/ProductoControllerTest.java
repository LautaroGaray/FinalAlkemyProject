package org.oauth.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.oauth.dto.ProductoDto;
import org.oauth.service.IProductoService;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

  @Test
  void listarTodos() {
    List<ProductoDto> productos = Arrays.asList(
        new ProductoDto("1", "Producto1", 100.0, true, new HashSet<>()),
        new ProductoDto("2", "Producto2", 200.0, false, new HashSet<>())
    );
    when(productoService.listarProductos()).thenReturn(productos);

    ResponseEntity<List<ProductoDto>> response = productoController.listarTodos();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(2, response.getBody().size());
    verify(productoService, times(1)).listarProductos();
  }

  @Test
  void crearProducto() {
    ProductoDto productoDto = new ProductoDto(null, "Producto1", 100.0, true, new HashSet<>());
    ProductoDto productoCreado = new ProductoDto("1", "Producto1", 100.0, true, new HashSet<>());
    when(productoService.crearProducto(productoDto)).thenReturn(productoCreado);

    ResponseEntity<?> response = productoController.crearProducto(productoDto);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("1", ((ProductoDto) response.getBody()).getId());
    verify(productoService, times(1)).crearProducto(productoDto);
  }

  @Test
  void actualizarProducto() {
    String id = "1";
    ProductoDto productoDto = new ProductoDto(null, "ProductoActualizado", 150.0, true, new HashSet<>());
    ProductoDto productoActualizado = new ProductoDto(id, "ProductoActualizado", 150.0, true, new HashSet<>());
    when(productoService.actualizarProducto(id, productoDto)).thenReturn(productoActualizado);

    ResponseEntity<?> response = productoController.actualizarProducto(id, productoDto);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(id, ((ProductoDto) response.getBody()).getId());
    verify(productoService, times(1)).actualizarProducto(id, productoDto);
  }

  @Test
  void eliminarProducto() {
    String id = "1";
    doNothing().when(productoService).eliminarProducto(id);

    ResponseEntity<?> response = productoController.eliminarProducto(id);

    assertEquals(204, response.getStatusCodeValue());
    verify(productoService, times(1)).eliminarProducto(id);
  }
}