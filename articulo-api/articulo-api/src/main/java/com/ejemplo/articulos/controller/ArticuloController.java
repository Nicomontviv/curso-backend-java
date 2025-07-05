package com.ejemplo.articulos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.articulos.model.Articulo;
import com.ejemplo.articulos.service.ArticuloService;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;

    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    // Endpoint para testear si el controlador está activo
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    // GET /api/articulos - Listar todos los artículos
    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listarArticulos();
    }

    // GET /api/articulos/{id} - Obtener artículo por ID (solo números)
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/articulos - Crear un nuevo artículo
    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return articuloService.guardarArticulo(articulo);
    }

    // PUT /api/articulos/{id} - Actualizar un artículo existente
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articuloService.actualizarArticulo(id, articulo));
    }

    // DELETE /api/articulos/{id} - Eliminar un artículo
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build();
    }
}
