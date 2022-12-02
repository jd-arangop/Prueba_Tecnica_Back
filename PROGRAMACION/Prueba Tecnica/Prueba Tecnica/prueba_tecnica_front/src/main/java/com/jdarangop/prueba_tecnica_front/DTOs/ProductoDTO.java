package com.jdarangop.prueba_tecnica_front.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private Long idProducto;
    private String nombre;
    private Integer precio;
    private Integer stock;
}
