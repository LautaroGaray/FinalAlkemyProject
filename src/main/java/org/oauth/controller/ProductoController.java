package org.oauth.controller;


import org.oauth.dto.ProductoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.oauth.service.IProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        logger.info("Listando todos los productos");
        try {

            List<ProductoDto> productos = productoService.listarProductos().join();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            logger.error("Error al listar productos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los productos");
        }
    }
    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDto productoDto) {
        logger.info("Solicitud para crear producto: {}", productoDto);
        try {
            ProductoDto creado = productoService.crearProducto(productoDto);
            logger.info("Producto creado con ID: {}", creado.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception e) {
            logger.error("Error al crear producto: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el producto");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable String id, @Valid @RequestBody ProductoDto productoDto) {
        logger.info("Solicitud para actualizar producto id: {}", id);
        try {
            ProductoDto actualizado = productoService.actualizarProducto(id, productoDto);
            logger.info("Producto actualizado con ID: {}", id);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            logger.error("Error al actualizar producto: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable String id) {
        logger.info("Solicitud para eliminar producto id: {}", id);
        try {
            productoService.eliminarProducto(id);
            logger.info("Producto eliminado con ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar producto: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe");
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<?> listarPorStock(
        @RequestParam boolean enStock,
        @RequestParam int page,
        @RequestParam int size) {
        logger.info("Listando productos por stock: {}", enStock);
        try {
            Page<ProductoDto> productos = productoService.listarProductosPorStock(enStock, page, size);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            logger.error("Error al listar productos por stock: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los productos");
        }
    }

}
