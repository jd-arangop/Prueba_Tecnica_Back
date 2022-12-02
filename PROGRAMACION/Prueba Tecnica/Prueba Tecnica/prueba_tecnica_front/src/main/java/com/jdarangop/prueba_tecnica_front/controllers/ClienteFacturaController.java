package com.jdarangop.prueba_tecnica_front.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdarangop.prueba_tecnica_front.DTOs.FacturaDTO;
import com.jdarangop.prueba_tecnica_front.DTOs.FacturaDetailDTO;
import com.jdarangop.prueba_tecnica_front.entities.FacturaEntity;
import com.jdarangop.prueba_tecnica_front.services.ClienteFacturaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientes")
public class ClienteFacturaController {
    
    @Autowired
    private ClienteFacturaService clienteFacturaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/{clienteId}/facturas/{facturaId}")
    @ResponseStatus(code = HttpStatus.OK)
    public FacturaDTO addFactura(@PathVariable("clienteId") Long clienteId, 
            @PathVariable("facturaId") Long facturaId) throws Exception{

        FacturaEntity facturaEntity = clienteFacturaService.addFactura(facturaId, clienteId);
        return modelMapper.map(facturaEntity, FacturaDTO.class);
    }

    @GetMapping(value = "/{clienteId}/facturas")
    @ResponseStatus(code = HttpStatus.OK)
    public List<FacturaDetailDTO> getFacturas(@PathVariable("clienteId") Long clienteId) throws Exception{

        List<FacturaEntity> facturas = clienteFacturaService.getFacturas(clienteId);
        return modelMapper.map(facturas, new TypeToken<List<FacturaDetailDTO>>(){
        }.getType());
    }

    @GetMapping(value = "/{clienteId}/facturas/{facturaId}")
    @ResponseStatus(code = HttpStatus.OK)
    public FacturaDetailDTO getFactura(@PathVariable("clienteId") Long clienteId, 
            @PathVariable("facturaId")Long facturaId) throws Exception{

        FacturaEntity facturaEntity = clienteFacturaService.getFactura(clienteId, facturaId);
        return modelMapper.map(facturaEntity, FacturaDetailDTO.class);
    }

    @PutMapping(value = "/{clienteId}/facturas")
    @ResponseStatus(code = HttpStatus.OK)
    public List<FacturaDetailDTO> replaceFacturas(@PathVariable("clienteId") Long clienteId,
            @RequestBody List<FacturaDetailDTO> facturas) throws Exception{

        List<FacturaEntity> facturaList = modelMapper.map(facturas, new TypeToken<List<FacturaEntity>>(){
        }.getType());
        List<FacturaEntity> result = clienteFacturaService.replaceFacturas(clienteId, facturaList);

        return modelMapper.map(result, new TypeToken<List<FacturaDetailDTO>>(){
        }.getType());
    }
}
