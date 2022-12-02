package com.jdarangop.prueba_tecnica_front.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdarangop.prueba_tecnica_front.entities.FacturaEntity;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {
    
}
