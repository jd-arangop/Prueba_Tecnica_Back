package com.jdarangop.prueba_tecnica_front.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdarangop.prueba_tecnica_front.DTOs.ClienteDTO;
import com.jdarangop.prueba_tecnica_front.DTOs.FacturaDetailDTO;
import com.jdarangop.prueba_tecnica_front.entities.FacturaEntity;
import com.jdarangop.prueba_tecnica_front.services.FacturaClienteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/facturas")
public class FacturaClienteController {
    
    @Autowired
    private FacturaClienteService facturaClienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping(value = "/{facturaId}/editorial")
    @ResponseStatus(code = HttpStatus.OK)
    public FacturaDetailDTO replaceCliente(@PathVariable("facturaId") Long facturaId, 
            @RequestBody ClienteDTO clienteDTO) throws Exception{

        FacturaEntity facturaEntity = facturaClienteService.replaceCliente(clienteDTO.getId(), facturaId);
        return modelMapper.map(facturaEntity, FacturaDetailDTO.class);
    }
}
