package com.jdarangop.prueba_tecnica_front.DTOs;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private Date fechaNacimiento;
    private String telefono;
    private String email;
}
