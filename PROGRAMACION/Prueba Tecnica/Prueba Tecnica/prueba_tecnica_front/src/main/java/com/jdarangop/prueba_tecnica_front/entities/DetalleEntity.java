package com.jdarangop.prueba_tecnica_front.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(DetalleId.class)
public class DetalleEntity {
    
    @Id
    private Long numDetalle;

    @Id
    @ManyToOne
    private FacturaEntity idFactura;

    private Integer cantidad;
    private Integer precio;

    @ManyToOne
    private ProductoEntity producto;
}
