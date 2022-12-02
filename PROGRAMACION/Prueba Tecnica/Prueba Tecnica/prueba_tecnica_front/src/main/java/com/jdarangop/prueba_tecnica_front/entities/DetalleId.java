package com.jdarangop.prueba_tecnica_front.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DetalleId implements Serializable{

    private Long numDetalle;

    private FacturaEntity idFactura;
}
