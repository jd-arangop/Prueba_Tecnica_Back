package com.jdarangop.prueba_tecnica_front.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClienteEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String apellido;
    private String direccion;
    private Date fechaNacimiento;
    private String telefono;
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<FacturaEntity> facturas = new ArrayList<>();
}
