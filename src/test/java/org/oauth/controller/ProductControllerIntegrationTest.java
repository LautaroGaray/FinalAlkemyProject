package org.oauth.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oauth.dto.ProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIntegrationTest {
/*
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void listarTodos() {
        ResponseEntity<ProductoDto[]> response = restTemplate.getForEntity("/api/productos", ProductoDto[].class);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void crearProducto() {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Producto1");
        productoDto.setPrecio(100.0);
        productoDto.setEnStock(true);
        productoDto.setColores(new HashSet<>());

        ResponseEntity<ProductoDto> response = restTemplate.postForEntity("/api/productos", productoDto, ProductoDto.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Producto1", response.getBody().getNombre());
    }

    @Test
    void actualizarProducto() {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("ProductoActualizado");
        productoDto.setPrecio(150.0);
        productoDto.setEnStock(true);
        productoDto.setColores(new HashSet<>());

        HttpEntity<ProductoDto> request = new HttpEntity<>(productoDto);
        ResponseEntity<ProductoDto> response = restTemplate.exchange("/api/productos/1", HttpMethod.PUT, request, ProductoDto.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("ProductoActualizado", response.getBody().getNombre());
    }

    @Test
    void eliminarProducto() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/productos/1", HttpMethod.DELETE, null, Void.class);

        assertEquals(204, response.getStatusCodeValue());
    }
*/}