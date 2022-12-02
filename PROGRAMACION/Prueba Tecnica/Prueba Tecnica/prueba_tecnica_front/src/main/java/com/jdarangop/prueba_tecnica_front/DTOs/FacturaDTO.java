package com.jdarangop.prueba_tecnica_front.DTOs;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturaDTO {
    private Long numFactura;
    private Date fecha;
    private ClienteDTO cliente;
}
