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

import com.jdarangop.prueba_tecnica_front.DTOs.ProductoDTO;
import com.jdarangop.prueba_tecnica_front.DTOs.ProductoDetailDTO;
import com.jdarangop.prueba_tecnica_front.entities.ProductoEntity;
import com.jdarangop.prueba_tecnica_front.services.ProductoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ProductoDetailDTO> findAll(){
        List<ProductoEntity> productos = productoService.getProductos();
        return modelMapper.map(productos, new TypeToken<List<ProductoDetailDTO>>(){
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ProductoDetailDTO findOne(@PathVariable("id") Long id) throws Exception{
        ProductoEntity productoEntity = productoService.getProducto(id);
        return modelMapper.map(productoEntity, ProductoDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductoDTO create(@RequestBody ProductoDTO productoDTO) throws Exception{
        ProductoEntity productoEntity = productoService.createProducto(modelMapper.map(productoDTO, ProductoEntity.class));
        return modelMapper.map(productoEntity, ProductoDTO.class);
    }
    
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ProductoDTO update(@PathVariable("id") Long id, @RequestBody ProductoDTO productoDTO)
        throws Exception{
            ProductoEntity productoEntity = productoService.updateProducto(id, modelMapper.map(productoDTO, ProductoEntity.class));
            return modelMapper.map(productoEntity, ProductoDTO.class);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws Exception{
        productoService.deleteProducto(id);
    }   
}
