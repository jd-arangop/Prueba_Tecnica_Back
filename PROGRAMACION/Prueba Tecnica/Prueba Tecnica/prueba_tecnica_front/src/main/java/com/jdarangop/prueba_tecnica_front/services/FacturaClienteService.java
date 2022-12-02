package com.jdarangop.prueba_tecnica_front.services;

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
public class FacturaClienteService {
    
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public FacturaEntity replaceCliente(Long clienteId, Long facturaId) throws Exception{

        log.info("Inicia el proceso de actualizar el cliente de la factura por ID");

        Optional<FacturaEntity> facturaEntity = facturaRepository.findById(facturaId);
        if (facturaEntity.isEmpty()){
            throw new Exception("Factura no encontrada");
        }

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if (clienteEntity.isEmpty()){
            throw new Exception("Cliente no encontrado");
        }

        facturaEntity.get().setCliente(clienteEntity.get());

        log.info("Termina el proceso de actualizar el cliente de la factura por ID");

        return facturaEntity.get();
    }

}
