package com.jdarangop.prueba_tecnica_front.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.ProductoEntity;
import com.jdarangop.prueba_tecnica_front.repositories.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;

    @Transactional
    public ProductoEntity createProducto(ProductoEntity productoEntity) throws Exception{

        log.info("Inicia el proceso de creacion de un producto");

        log.info("Termina el proceso de creacion de un producto");

        return productoRepository.save(productoEntity);
    }

    @Transactional
    public List<ProductoEntity> getProductos(){
        log.info("Inicia el proceso de consulta de todos los productos");
        return productoRepository.findAll();
    }

    @Transactional
    public ProductoEntity getProducto(Long productoId) throws Exception{

        log.info("Inicia el proceso de consultar un producto por id");

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if(productoEntity.isEmpty()){
            throw new Exception("No existe un producto con el ID dado");
        }
    
        log.info("Termina el proceso de consultar un producto por id");

        return productoEntity.get();
    }

    @Transactional
    public ProductoEntity updateProducto(Long productoId, ProductoEntity producto) throws Exception{

        log.info("Inicia el proceso de actualizar un producto con ID = " + productoId);

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if(productoEntity.isEmpty()){
            throw new Exception("No existe un producto con el ID dado");
        }
        producto.setIdProducto(productoId);

        log.info("Termina el proceso de actualizar un cliente");
        return productoRepository.save(producto);
    }

    @Transactional
    public void deleteProducto(Long productoId) throws Exception{

        log.info("Inicia el proceso de borrar el producto con el ID =" + productoId);

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if(productoEntity.isEmpty()){
            throw new Exception("No existe un producto con el ID dado");
        }

        productoRepository.deleteById(productoId);

        log.info("Termina el proceso de borrar el producto con el ID =" + productoId);
    }
}
