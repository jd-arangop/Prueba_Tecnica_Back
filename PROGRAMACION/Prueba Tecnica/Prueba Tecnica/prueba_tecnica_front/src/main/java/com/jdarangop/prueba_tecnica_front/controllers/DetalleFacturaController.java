package com.jdarangop.prueba_tecnica_front.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdarangop.prueba_tecnica_front.DTOs.DetalleDTO;
import com.jdarangop.prueba_tecnica_front.DTOs.FacturaDTO;
import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.services.DetalleFacturaService;

@RestController
@RequestMapping("/detalles")
public class DetalleFacturaController {
    
    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Autowired ModelMapper modelMapper;

    @PutMapping(value = "/{detalleId}/factura")
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO replaceCliente(@PathVariable("detalleId") DetalleId detalleId, 
            @RequestBody FacturaDTO facturaDTO) throws Exception{

        DetalleEntity detalleEntity = detalleFacturaService.replaceFactura(facturaDTO.getNumFactura(), detalleId);
        return modelMapper.map(detalleEntity, DetalleDTO.class);
    }
}
