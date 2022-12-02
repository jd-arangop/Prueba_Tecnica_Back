package com.jdarangop.prueba_tecnica_front.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.jdarangop.prueba_tecnica_front.services.FacturaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/facturas")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<FacturaDetailDTO> findAll(){
        List<FacturaEntity> facturas = facturaService.getfacturas();
        return modelMapper.map(facturas, new TypeToken<List<FacturaDetailDTO>>(){
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public FacturaDetailDTO findOne(@PathVariable("id") Long id) throws Exception{
        FacturaEntity facturaEntity = facturaService.getFactura(id);
        return modelMapper.map(facturaEntity, FacturaDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public FacturaDTO create(@RequestBody FacturaDTO facturaDTO) throws Exception{
        FacturaEntity facturaEntity = facturaService.createFactura(modelMapper.map(facturaDTO, FacturaEntity.class));
        return modelMapper.map(facturaEntity, FacturaDTO.class);
    }
    
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public FacturaDTO update(@PathVariable("id") Long id, @RequestBody FacturaDTO facturaDTO)
        throws Exception{
            FacturaEntity facturaEntity = facturaService.updateFactura(id, modelMapper.map(facturaDTO, FacturaEntity.class));
            return modelMapper.map(facturaEntity, FacturaDTO.class);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws Exception{
        facturaService.deleteFactura(id);
    }
}
