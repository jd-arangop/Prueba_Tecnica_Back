package com.jdarangop.prueba_tecnica_front.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.repositories.DetalleRepository;
import com.jdarangop.prueba_tecnica_front.repositories.FacturaRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetalleService {
    
    @Autowired
    DetalleRepository detalleRepository;

    @Autowired
    FacturaRepository facturaRepository;
    
    @Transactional
    public DetalleEntity createDetalle(DetalleEntity detalleEntity) throws Exception{

        log.info("Inicia el proceso de creacion de un detalle");

        log.info("Termina el proceso de creacion de un detalle");

        return detalleRepository.save(detalleEntity);
    }

    @Transactional
    public List<DetalleEntity> getDetalles(){
        log.info("Inicia el proceso de consulta de todos los detalles");
        return detalleRepository.findAll();
    }

    @Transactional
    public DetalleEntity getDetalle(DetalleId detalleId) throws Exception{

        log.info("Inicia el proceso de consultar un detalle por id");

        Optional<DetalleEntity> detalleEntity = detalleRepository.findById(detalleId);
        if(detalleEntity.isEmpty()){
            throw new Exception("No existe un detalle con el ID dado");
        }
    
        log.info("Termina el proceso de consultar un detalle por id");

        return detalleEntity.get();
    }

    @Transactional
    public DetalleEntity updateDetalle(DetalleId detalleId, DetalleEntity detalle) throws Exception{

        log.info("Inicia el proceso de actualizar un detalle");

        Optional<DetalleEntity> clienteEntity = detalleRepository.findById(detalleId);
        if(clienteEntity.isEmpty()){
            throw new Exception("No existe un detalle con el ID dado");
        }
        detalle.setIdFactura(detalleId.getIdFactura());
        detalle.setNumDetalle(detalleId.getNumDetalle());

        log.info("Termina el proceso de actualizar un detalle");
        return detalleRepository.save(detalle);
    }

    @Transactional
    public void deleteDetalle(DetalleId detalleId) throws Exception{

        log.info("Inicia el proceso de borrar un detalle");

        Optional<DetalleEntity> clienteEntity = detalleRepository.findById(detalleId);
        if(clienteEntity.isEmpty()){
            throw new Exception("No existe un detalle con el ID dado");
        }

        detalleRepository.deleteById(detalleId);

        log.info("Termina el proceso de borrar un detalle por ID");
    }
}
