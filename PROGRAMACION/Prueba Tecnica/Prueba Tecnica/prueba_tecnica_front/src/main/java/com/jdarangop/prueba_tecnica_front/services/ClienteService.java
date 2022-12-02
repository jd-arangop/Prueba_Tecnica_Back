package com.jdarangop.prueba_tecnica_front.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdarangop.prueba_tecnica_front.entities.ClienteEntity;
import com.jdarangop.prueba_tecnica_front.repositories.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public ClienteEntity createCliente(ClienteEntity clienteEntity) throws Exception{

        log.info("Inicia el proceso de creacion de un cliente");

        log.info("Termina el proceso de creacion de un cliente");

        return clienteRepository.save(clienteEntity);
    }

    @Transactional
    public List<ClienteEntity> getClientes(){
        log.info("Inicia el proceso de consulta de todos los clientes");
        return clienteRepository.findAll();
    }

    @Transactional
    public ClienteEntity getCliente(Long clienteId) throws Exception{

        log.info("Inicia el proceso de consultar un cliente por id");

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if(clienteEntity.isEmpty()){
            throw new Exception("No existe un cliente con el ID dado");
        }
    
        log.info("Termina el proceso de consultar un cliente por id");

        return clienteEntity.get();
    }

    @Transactional
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity cliente) throws Exception{

        log.info("Inicia el proceso de actualizar un cliente con ID = " + clienteId);

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if(clienteEntity.isEmpty()){
            throw new Exception("No existe un cliente con el ID dado");
        }
        cliente.setIdCliente(clienteId);

        log.info("Termina el proceso de actualizar un cliente");
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deleteCliente(Long clienteId) throws Exception{

        log.info("Inicia el proceso de borrar el cliente con el ID =" + clienteId);

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
        if(clienteEntity.isEmpty()){
            throw new Exception("No existe un cliente con el ID dado");
        }

        clienteRepository.deleteById(clienteId);

        log.info("Termina el proceso de borrar el cliente con el ID =" + clienteId);
    }
}
