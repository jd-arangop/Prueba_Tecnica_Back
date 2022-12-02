package com.jdarangop.prueba_tecnica_front.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.entities.FacturaEntity;
import com.jdarangop.prueba_tecnica_front.repositories.DetalleRepository;
import com.jdarangop.prueba_tecnica_front.repositories.FacturaRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FacturaDetalleService {
    
    @Autowired
    private DetalleRepository detalleRepository; 

    @Autowired
    private FacturaRepository facturaRepository;

    @Transactional
    public DetalleEntity addDetalle(DetalleId detalleId, Long facturaId) throws Exception{ 

        log.info("Inicia el proceso de agregarle una detalle a una factura por ID");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("No se encuentra la factura");
        }

        Optional<DetalleEntity> detalleEntity = detalleRepository.findById(detalleId);
        if (detalleEntity.isEmpty()){
            throw new Exception("No se encuentra el detalle");   
        }

        detalleEntity.get().setIdFactura(facturaEntity.get());

        log.info("Termina el proceso de agregarle un detalle a una factura por ID");
        return detalleEntity.get();
    }

    @Transactional
    public List<DetalleEntity> getDetalles(Long facturaId) throws Exception{

        log.info("Inicia proceso de consultar las facturas asociadas a un cliente por ID");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("No se encuentra la factura");
        }

        return facturaEntity.get().getDetalles();
    }

    @Transactional
    public DetalleEntity getDetalle(Long facturaId, DetalleId detalleId) throws Exception{

        log.info("Inicia el proceso de consultar una detalle por ID de una factura");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("No se encuentra la factura");
        }

        Optional<DetalleEntity> detalleEntity = detalleRepository.findById(detalleId);
        if (detalleEntity.isEmpty()){
            throw new Exception("No se encuentra el detalle");
        }

        log.info("Termina proceso de consultar el detalle por ID de una factura");

        if(!facturaEntity.get().getDetalles().contains(detalleEntity.get())){
            throw new Exception("El detalle no esta asociado con la factura");
        }

        return detalleEntity.get();
    }

    @Transactional
    public List<DetalleEntity> replaceDetalles(Long facturaId, List<DetalleEntity> detalles) throws Exception{
        log.info("Inicia el proceso de cambiar laos detalles de una factura");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("No se encuentra la factura");
        }

        for(DetalleEntity detalle: detalles){
            Optional<DetalleEntity> detalleEntity = detalleRepository.findById(new DetalleId(detalle.getNumDetalle(), detalle.getIdFactura()));
            if (detalleEntity.isEmpty()){
                throw new Exception("No se encuentra el detalle");
            }
            detalleEntity.get().setIdFactura(facturaEntity.get());
        }

        return detalles;
    }
}
