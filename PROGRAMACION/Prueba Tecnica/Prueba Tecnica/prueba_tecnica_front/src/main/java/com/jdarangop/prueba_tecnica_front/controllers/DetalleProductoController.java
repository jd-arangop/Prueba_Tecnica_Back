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

import com.jdarangop.prueba_tecnica_front.DTOs.DetalleDTO;
import com.jdarangop.prueba_tecnica_front.DTOs.ProductoDTO;
import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.services.DetalleProductoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/detalles")
public class DetalleProductoController {
    
    @Autowired
    private DetalleProductoService detalleProductoService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping(value = "/{detalleId}/producto")
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO replaceCliente(@PathVariable("detalleId") DetalleId detalleId, 
            @RequestBody ProductoDTO productoDTO) throws Exception{

        DetalleEntity detalleEntity = detalleProductoService.replaceProducto(productoDTO.getIdProducto(), detalleId);
        return modelMapper.map(detalleEntity, DetalleDTO.class);
    }
}
