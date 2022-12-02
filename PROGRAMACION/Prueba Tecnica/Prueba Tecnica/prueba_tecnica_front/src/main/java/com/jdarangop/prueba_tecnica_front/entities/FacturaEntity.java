package com.jdarangop.prueba_tecnica_front.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FacturaEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numFactura;

    private Date fecha;

    @ManyToOne
    private ClienteEntity cliente;

    @OneToMany (mappedBy = "idFactura")
    private List<DetalleEntity> detalles = new ArrayList<>();
}
