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
public class ClienteFacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public FacturaEntity addFactura(Long facturaId, Long clienteId) throws Exception{ 

        log.info("Inicia el proceso de agregarle una factura a un cliente por ID");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("No se encuentra la factura");
        }

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if (clienteEntity.isEmpty()){
            throw new Exception("No se encuentra el cliente");   
        }

        facturaEntity.get().setCliente(clienteEntity.get());

        log.info("Termina el proceso de agregarle una factura a un cliente por ID");
        return facturaEntity.get();
    }

    @Transactional
    public List<FacturaEntity> getFacturas(Long clienteId) throws Exception{

        log.info("Inicia proceso de consultar las facturas asociadas a un cliente por ID");

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if (clienteEntity.isEmpty()){
            throw new Exception("No se encuentra el cliente");
        }

        return clienteEntity.get().getFacturas();
    }

    @Transactional
    public FacturaEntity getFactura(Long clienteId, Long facturaId) throws Exception{

        log.info("Inicia el proceso de consultar una factura por ID de un cliente");

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if (clienteEntity.isEmpty()){
            throw new Exception("No se encuentra el cliente");
        }

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("No se encuentra la factura");
        }

        log.info("Termina proceso de consultar la factura por ID de un cliente");

        if(!clienteEntity.get().getFacturas().contains(facturaEntity.get())){
            throw new Exception("La factura no esta asociada al cliente");
        }

        return facturaEntity.get();
    }

    @Transactional
    public List<FacturaEntity> replaceFacturas(Long clienteId, List<FacturaEntity> facturas) throws Exception{
        log.info("Inicia el proceso de cambiar las facturas de un cliente");

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if (clienteEntity.isEmpty()){
            throw new Exception("No se encuentra el cliente");
        }

        for(FacturaEntity factura: facturas){
            Optional<FacturaEntity> facturaEntity = facturaRepository.findById(factura.getNumFactura());
            if (facturaEntity.isEmpty()){
                throw new Exception("No se encuentra la factura");
            }
            facturaEntity.get().setCliente(clienteEntity.get());
        }

        return facturas;
    }
}
