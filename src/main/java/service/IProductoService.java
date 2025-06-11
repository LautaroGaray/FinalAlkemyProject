package service;


import com.fasterxml.jackson.core.JsonProcessingException;
import dto.ProductoDto;

import java.util.List;


public interface IProductoService {

    ProductoDto crearProducto(ProductoDto productoDto) throws JsonProcessingException;
    ProductoDto obtenerPorId(String id);
    List<ProductoDto> listarProductos();
    void eliminarProducto(String id);
    ProductoDto obtenerPorNombre(String nombre);
    public ProductoDto actualizarProducto(String id, ProductoDto productoDto);
}
