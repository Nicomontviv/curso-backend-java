/* 
// Paquete del repositorio de implementación
package com.ejemplo.articulos.repository;

import com.ejemplo.articulos.model.Articulo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Anotación que marca esta clase como componente de repositorio
@Repository
public class ArticuloRepositoryImpl implements ArticuloRepository {

    // Lista que actúa como base de datos en memoria
    // que es private final List<Articulo> articulos = new ArrayList<>();
    // private final significa que la lista no puede ser reasignada,
    // pero los elementos dentro de la lista pueden ser modificados.
    // Esto es útil para evitar que la referencia a la lista cambie,
    // pero permite agregar, eliminar o modificar los artículos dentro de la lista.
    // ArrayList es una implementación de la interfaz List que permite almacenar elementos de manera dinámica.  
    // final es como cuando a los arrays en js le poniamos const para que no modificara la direccion de espacio en memoria es decir la referencia, el objeto sigue siendo mutable
    // y podemos agregar, eliminar o modificar los artículos dentro de la lista.
    private final List<Articulo> articulos = new ArrayList<>();

    // Variable que simula un contador automático de IDs
    private long idCounter = 1;

    // Devuelve todos los artículos de la lista
    @Override
    public List<Articulo> findAll() {
        return new ArrayList<>(articulos);
    }

    // Busca un artículo por su ID
    @Override
    public Optional<Articulo> findById(Long id) {
        return articulos.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    // Guarda o actualiza un artículo
    @Override
    public Articulo save(Articulo articulo) {
        if (articulo.getId() == null) {
            articulo.setId(idCounter++); // Asigna ID si es nuevo
            articulos.add(articulo); // Agrega a la lista
        } else {
            deleteById(articulo.getId()); // Elimina el anterior
            articulos.add(articulo); // Agrega el actualizado
        }
        return articulo;
    }

    // Elimina un artículo por ID
    @Override
    public void deleteById(Long id) {
        articulos.removeIf(a -> a.getId().equals(id));
    }
}
*/