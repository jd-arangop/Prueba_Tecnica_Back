package com.jdarangop.prueba_tecnica_front.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdarangop.prueba_tecnica_front.entities.DetalleEntity;
import com.jdarangop.prueba_tecnica_front.entities.DetalleId;

@Repository
public interface DetalleRepository extends JpaRepository<DetalleEntity, DetalleId>{
    
}
