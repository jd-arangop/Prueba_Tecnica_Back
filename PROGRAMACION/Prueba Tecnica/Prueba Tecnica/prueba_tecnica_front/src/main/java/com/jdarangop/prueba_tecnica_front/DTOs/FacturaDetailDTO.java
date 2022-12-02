package com.jdarangop.prueba_tecnica_front.DTOs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturaDetailDTO extends FacturaDTO{
    private List<DetalleDTO> detalles;
}
