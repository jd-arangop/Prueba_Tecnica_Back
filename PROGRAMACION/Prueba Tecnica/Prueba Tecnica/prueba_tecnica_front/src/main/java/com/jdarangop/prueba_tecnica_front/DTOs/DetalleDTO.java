package com.jdarangop.prueba_tecnica_front.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleDTO {
    private Long numDetalle;
    private FacturaDTO idFactura;
    private ProductoDTO producto;
    private Integer cantidad;
    private Integer precio;
}
