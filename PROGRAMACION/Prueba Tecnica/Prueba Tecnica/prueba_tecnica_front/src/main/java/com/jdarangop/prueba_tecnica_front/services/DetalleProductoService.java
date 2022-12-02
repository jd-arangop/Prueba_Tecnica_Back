package com.jdarangop.prueba_tecnica_front.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.ProductoEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.repositories.DetalleRepository;
import com.jdarangop.prueba_tecnica_front.repositories.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetalleProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Transactional
    public DetalleEntity replaceProducto(Long productoId, DetalleId detalleId) throws Exception{

        log.info("Inicia el proceso de actualizar el producto de un detalle por ID");

        Optional<DetalleEntity> detalleEntity = detalleRepository.findById(detalleId);
        if (detalleEntity.isEmpty()){
            throw new Exception("Detalle no encontrada");
        }

        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if (productoEntity.isEmpty()){
            throw new Exception("Producto no encontrado");
        }

        detalleEntity.get().setProducto(productoEntity.get());

        log.info("Termina el proceso de actualizar el cliente de la factura por ID");

        return detalleEntity.get();
    }
}
