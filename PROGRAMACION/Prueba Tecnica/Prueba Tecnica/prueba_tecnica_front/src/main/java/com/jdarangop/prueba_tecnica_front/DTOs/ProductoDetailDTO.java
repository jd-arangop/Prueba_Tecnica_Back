package com.jdarangop.prueba_tecnica_front.DTOs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDetailDTO extends ProductoDTO{
    private List<DetalleDTO> detalles;
}
