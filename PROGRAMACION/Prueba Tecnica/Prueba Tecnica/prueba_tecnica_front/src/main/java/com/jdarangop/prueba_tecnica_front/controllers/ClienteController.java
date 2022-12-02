package com.jdarangop.prueba_tecnica_front.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdarangop.prueba_tecnica_front.DTOs.ClienteDTO;
import com.jdarangop.prueba_tecnica_front.DTOs.ClienteDetailDTO;
import com.jdarangop.prueba_tecnica_front.entities.ClienteEntity;
import com.jdarangop.prueba_tecnica_front.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ClienteDetailDTO> findAll(){
        List<ClienteEntity> clientes = clienteService.getClientes();
        return modelMapper.map(clientes, new TypeToken<List<ClienteDetailDTO>>(){
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ClienteDetailDTO findOne(@PathVariable("id") Long id) throws Exception{
        ClienteEntity clienteEntity = clienteService.getCliente(id);
        return modelMapper.map(clienteEntity, ClienteDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClienteDTO create(@RequestBody ClienteDTO clienteDTO) throws Exception{
        ClienteEntity clienteEntity = clienteService.createCliente(modelMapper.map(clienteDTO, ClienteEntity.class));
        return modelMapper.map(clienteEntity, ClienteDTO.class);
    }
    
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ClienteDTO update(@PathVariable("id") Long id, @RequestBody ClienteDTO clienteDTO)
        throws Exception{
            ClienteEntity clienteEntity = clienteService.updateCliente(id, modelMapper.map(clienteDTO, ClienteEntity.class));
            return modelMapper.map(clienteEntity, ClienteDTO.class);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws Exception{
        clienteService.deleteCliente(id);
    }
}
