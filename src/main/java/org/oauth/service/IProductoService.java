package org.oauth.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.oauth.dto.ProductoDto;
import org.oauth.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface IProductoService {

    ProductoDto crearProducto(ProductoDto productoDto) throws JsonProcessingException;
    ProductoDto obtenerPorId(String id);
    //List<ProductoDto> listarProductos();
    CompletableFuture<List<ProductoDto>> listarProductos();
    void eliminarProducto(String id);
    ProductoDto obtenerPorNombre(String nombre);
    public ProductoDto actualizarProducto(String id, ProductoDto productoDto);
    Page<ProductoDto> listarProductosPorStock(boolean enStock, Pageable pageable);
}
