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
import com.jdarangop.prueba_tecnica_front.services.ProductoDetalleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productos")
public class ProductoDetalleController {

    @Autowired
    private ProductoDetalleService productoDetalleService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/{productoId}/detalles/{detalleId}")
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO addDetalle(@PathVariable("productoId") Long productoId, 
            @PathVariable("detalleId") DetalleId detalleId) throws Exception{

        DetalleEntity productoEntity = productoDetalleService.addDetalle(detalleId, productoId);
        return modelMapper.map(productoEntity, DetalleDTO.class);
    }

    @GetMapping(value = "/{productoId}/detalles")
    @ResponseStatus(code = HttpStatus.OK)
    public List<DetalleDTO> getDetalles(@PathVariable("productoId") Long productoId) throws Exception{

        List<DetalleEntity> productos = productoDetalleService.getDetalles(productoId);
        return modelMapper.map(productos, new TypeToken<List<DetalleDTO>>(){
        }.getType());
    }

    @GetMapping(value = "/{productoId}/detalles/{detalleId}")
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO getDetalle(@PathVariable("productoId") Long productoId, 
            @PathVariable("productoId")DetalleId detalleId) throws Exception{

        DetalleEntity productoEntity = productoDetalleService.getDetalle(productoId, detalleId);
        return modelMapper.map(productoEntity, DetalleDTO.class);
    }

    @PutMapping(value = "/{productoId}/detalles")
    @ResponseStatus(code = HttpStatus.OK)
    public List<DetalleDTO> replaceDetalles(@PathVariable("productoId") Long productoId,
            @RequestBody List<DetalleDTO> productos) throws Exception{

        List<DetalleEntity> productoList = modelMapper.map(productos, new TypeToken<List<DetalleEntity>>(){
        }.getType());
        List<DetalleEntity> result = productoDetalleService.replaceDetalles(productoId, productoList);

        return modelMapper.map(result, new TypeToken<List<DetalleDTO>>(){
        }.getType());
    }
    
}
