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

import com.jdarangop.prueba_tecnica_front.DTOs.DetalleDTO;
import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.services.FacturaDetalleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/facturas")
public class FacturaDetalleController {
    
    @Autowired
    private FacturaDetalleService facturaDetalleService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/{facturaId}/detalles/{detalleId}")
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO addDetalle(@PathVariable("facturaId") Long facturaId, 
            @PathVariable("detalleId") DetalleId detalleId) throws Exception{

        DetalleEntity facturaEntity = facturaDetalleService.addDetalle(detalleId, facturaId);
        return modelMapper.map(facturaEntity, DetalleDTO.class);
    }

    @GetMapping(value = "/{facturaId}/detalles")
    @ResponseStatus(code = HttpStatus.OK)
    public List<DetalleDTO> getDetalles(@PathVariable("facturaId") Long facturaId) throws Exception{

        List<DetalleEntity> facturas = facturaDetalleService.getDetalles(facturaId);
        return modelMapper.map(facturas, new TypeToken<List<DetalleDTO>>(){
        }.getType());
    }

    @GetMapping(value = "/{facturaId}/detalles/{detalleId}")
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO getDetalle(@PathVariable("facturaId") Long facturaId, 
            @PathVariable("facturaId")DetalleId detalleId) throws Exception{

        DetalleEntity facturaEntity = facturaDetalleService.getDetalle(facturaId, detalleId);
        return modelMapper.map(facturaEntity, DetalleDTO.class);
    }

    @PutMapping(value = "/{facturaId}/detalles")
    @ResponseStatus(code = HttpStatus.OK)
    public List<DetalleDTO> replaceDetalles(@PathVariable("facturaId") Long facturaId,
            @RequestBody List<DetalleDTO> facturas) throws Exception{

        List<DetalleEntity> facturaList = modelMapper.map(facturas, new TypeToken<List<DetalleEntity>>(){
        }.getType());
        List<DetalleEntity> result = facturaDetalleService.replaceDetalles(facturaId, facturaList);

        return modelMapper.map(result, new TypeToken<List<DetalleDTO>>(){
        }.getType());
    }
}
