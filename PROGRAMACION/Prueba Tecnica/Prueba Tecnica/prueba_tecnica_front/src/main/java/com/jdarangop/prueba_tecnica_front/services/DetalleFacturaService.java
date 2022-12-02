package com.jdarangop.prueba_tecnica_front.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.FacturaEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.repositories.DetalleRepository;
import com.jdarangop.prueba_tecnica_front.repositories.FacturaRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetalleFacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Transactional
    public DetalleEntity replaceFactura(Long facturaId, DetalleId detalleId) throws Exception{

        log.info("Inicia el proceso de actualizar el cliente de la factura por ID");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("Factura no encontrada");
        }

        Optional<DetalleEntity> detalleEntity = detalleRepository.findById(detalleId);
        if (detalleEntity.isEmpty()){
            throw new Exception("Detalle no encontrado");
        }

        detalleEntity.get().setIdFactura(facturaEntity.get());

        log.info("Termina el proceso de actualizar la factura de un detalle por ID");

        return detalleEntity.get();
    }
}
