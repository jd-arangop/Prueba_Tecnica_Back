package com.jdarangop.prueba_tecnica_front.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.ClienteEntity;
import com.jdarangop.prueba_tecnica_front.entities.FacturaEntity;
import com.jdarangop.prueba_tecnica_front.repositories.ClienteRepository;
import com.jdarangop.prueba_tecnica_front.repositories.FacturaRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FacturaService {
    
    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public FacturaEntity createFactura(FacturaEntity facturaEntity) throws Exception{

        log.info("Inicia el proceso de creacion de un factura");

        if (facturaEntity.getCliente() == null){
            throw new Exception("El ID del cliente es nulo");
        }

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(facturaEntity.getCliente().getIdCliente());
        if (clienteEntity.isEmpty()){
            throw new Exception("El cliente no existe en la base de datos");
        }

        log.info("Termina el proceso de creacion de un factura");

        return facturaRepository.save(facturaEntity);
    }

    @Transactional
    public List<FacturaEntity> getfacturas(){
        log.info("Inicia el proceso de consulta de todos los facturas");
        return facturaRepository.findAll();
    }

    @Transactional
    public FacturaEntity getFactura(Long facturaId) throws Exception{

        log.info("Inicia el proceso de consultar un factura por id");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if(facturaEntity.isEmpty()){
            throw new Exception("No existe un factura con el ID dado");
        }
    
        log.info("Termina el proceso de consultar un factura por id");

        return facturaEntity.get();
    }

    @Transactional
    public FacturaEntity updateFactura(Long facturaId, FacturaEntity factura) throws Exception{

        log.info("Inicia el proceso de actualizar un factura con ID = " + facturaId);

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if(facturaEntity.isEmpty()){
            throw new Exception("No existe un factura con el ID dado");
        }
        factura.setNumFactura(facturaId);

        log.info("Termina el proceso de actualizar un factura");
        return facturaRepository.save(factura);
    }

    @Transactional
    public void deleteFactura(Long facturaId) throws Exception{

        log.info("Inicia el proceso de borrar el factura con el ID =" + facturaId);

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if(facturaEntity.isEmpty()){
            throw new Exception("No existe un factura con el ID dado");
        }

        facturaRepository.deleteById(facturaId);

        log.info("Termina el proceso de borrar el factura con el ID =" + facturaId);
    }
}
