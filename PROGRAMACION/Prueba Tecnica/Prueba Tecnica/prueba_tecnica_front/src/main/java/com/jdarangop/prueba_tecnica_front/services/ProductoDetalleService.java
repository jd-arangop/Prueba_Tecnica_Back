package com.jdarangop.prueba_tecnica_front.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.entities.ProductoEntity;
import com.jdarangop.prueba_tecnica_front.repositories.DetalleRepository;
import com.jdarangop.prueba_tecnica_front.repositories.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoDetalleService {
    
    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public DetalleEntity addDetalle(DetalleId detalleId, Long productoId) throws Exception{ 

        log.info("Inicia el proceso de agregarle un producto a un detalle por ID");

        Optional<DetalleEntity> detalleEntity = detalleRepository.findById(detalleId);
        if (detalleEntity.isEmpty()){
            throw new Exception("No se encuentra la detalle");
        }

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if (productoEntity.isEmpty()){
            throw new Exception("No se encuentra el producto");   
        }

        detalleEntity.get().setProducto(productoEntity.get());

        log.info("Termina el proceso de agregarle una detalle a un producto por ID");
        return detalleEntity.get();
    }

    @Transactional
    public List<DetalleEntity> getDetalles(Long productoId) throws Exception{

        log.info("Inicia proceso de consultar las detalles asociadas a un producto por ID");

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if (productoEntity.isEmpty()){
            throw new Exception("No se encuentra el producto");
        }

        return productoEntity.get().getDetalles();
    }

    @Transactional
    public DetalleEntity getDetalle(Long productoId, DetalleId detalleId) throws Exception{

        log.info("Inicia el proceso de consultar una detalle por ID de un producto");

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if (productoEntity.isEmpty()){
            throw new Exception("No se encuentra el producto");
        }

        Optional<DetalleEntity> detalleEntity = detalleRepository.findById(detalleId);
        if (detalleEntity.isEmpty()){
            throw new Exception("No se encuentra la detalle");
        }

        log.info("Termina proceso de consultar la detalle por ID de un producto");

        if(!productoEntity.get().getDetalles().contains(detalleEntity.get())){
            throw new Exception("La detalle no esta asociada al producto");
        }

        return detalleEntity.get();
    }

    @Transactional
    public List<DetalleEntity> replaceDetalles(Long productoId, List<DetalleEntity> detalles) throws Exception{
        log.info("Inicia el proceso de cambiar las detalles de un producto");

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if (productoEntity.isEmpty()){
            throw new Exception("No se encuentra el producto");
        }

        for(DetalleEntity detalle: detalles){
            Optional<DetalleEntity> detalleEntity = detalleRepository.findById(new DetalleId(detalle.getNumDetalle(),detalle.getIdFactura()));
            if (detalleEntity.isEmpty()){
                throw new Exception("No se encuentra la detalle");
            }
            detalleEntity.get().setProducto(productoEntity.get());
        }

        return detalles;
    }


}
