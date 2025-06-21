package org.oauth.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.oauth.dto.ProductoDto;
import lombok.RequiredArgsConstructor;
import org.oauth.mapper.IProductoMapper;
import org.oauth.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.oauth.repository.IProductoRepository;
import org.oauth.service.IProductoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IProductoMapper productoMapper;

    @Override
    public ProductoDto crearProducto(ProductoDto productoDto) throws JsonProcessingException {
        Producto producto = productoMapper.toEntity(productoDto);
        Producto productoGuardado = productoRepository.save(producto);
        System.out.println("crearProducto - Service...");
        System.out.print(producto.toString());
        return productoMapper.toDto(productoGuardado);
    }

    @Override
    public ProductoDto obtenerPorId(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
        return productoMapper.toDto(producto);
    }

    @Override
    public List<ProductoDto> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(String id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("El producto no existe");
        }
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDto actualizarProducto(String id, ProductoDto productoDto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));

        productoExistente.setNombre(productoDto.getNombre());
        productoExistente.setPrecio(productoDto.getPrecio());
        productoExistente.setEnStock(productoDto.isEnStock());
        productoExistente.setColores(productoDto.getColores());

        Producto productoActualizado = productoRepository.save(productoExistente);
        return productoMapper.toDto(productoActualizado);
    }

    @Override
    public ProductoDto obtenerPorNombre(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
        return productoMapper.toDto(producto);
    }
}
