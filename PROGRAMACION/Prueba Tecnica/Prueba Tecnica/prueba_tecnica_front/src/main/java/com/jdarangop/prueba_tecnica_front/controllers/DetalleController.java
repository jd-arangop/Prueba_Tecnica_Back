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

import com.jdarangop.prueba_tecnica_front.DTOs.DetalleDTO;
import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;
import com.jdarangop.prueba_tecnica_front.entities.FacturaEntity;
import com.jdarangop.prueba_tecnica_front.services.DetalleService;
import com.jdarangop.prueba_tecnica_front.services.FacturaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/detalles")
public class DetalleController {
    
    @Autowired
    private DetalleService detalleService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<DetalleDTO> findAll(){
        List<DetalleEntity> detalles = detalleService.getDetalles();
        return modelMapper.map(detalles, new TypeToken<List<DetalleDTO>>(){
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO findOne(@PathVariable("id") Long idDetalle, Long numFactura) throws Exception{
        FacturaEntity factura = facturaService.getFactura(numFactura);
        DetalleEntity detalleEntity = detalleService.getDetalle(new DetalleId(idDetalle, factura));
        return modelMapper.map(detalleEntity, DetalleDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<DetalleDTO> create(@RequestBody List<DetalleDTO> detallesDTO) throws Exception{
        List<DetalleEntity> detalleList = modelMapper.map(detallesDTO, new TypeToken<List<DetalleEntity>>(){
        }.getType());

        for (int i=0; i<detalleList.size();i++){
            detalleService.createDetalle(detalleList.get(i));
        }
        
        return detallesDTO;
    }
    
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public DetalleDTO update(@PathVariable("id") Long idDetalle, Long numFactura, @RequestBody DetalleDTO detalleDTO)
        throws Exception{
            FacturaEntity factura = facturaService.getFactura(numFactura);
            DetalleEntity detalleEntity = detalleService.updateDetalle(new DetalleId(idDetalle,factura), modelMapper.map(detalleDTO, DetalleEntity.class));
            return modelMapper.map(detalleEntity, DetalleDTO.class);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id, Long numFactura) throws Exception{
        FacturaEntity factura = facturaService.getFactura(numFactura);
        detalleService.deleteDetalle(new DetalleId(id, factura));
    }
}
