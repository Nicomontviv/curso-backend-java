package com.ejemplo.articulos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.articulos.model.Articulo;

// @Repository es opcional pero recomendable para claridad
@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    // No hace falta implementar nada, JpaRepository ya lo hace por vos
}
