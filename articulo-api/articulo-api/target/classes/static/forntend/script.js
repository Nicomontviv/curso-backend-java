const API_URL = 'http://localhost:8080/api/articulos';

document.addEventListener('DOMContentLoaded', cargarArticulos);

function cargarArticulos() {
  fetch(API_URL)
    .then(response => {
      if (!response.ok) throw new Error('Error en la respuesta: ' + response.status);
      return response.json();
    })
    .then(articulos => {
      const tabla = document.getElementById('tabla-articulos');
      tabla.innerHTML = '';
      articulos.forEach(articulo => {
        tabla.innerHTML += `
          <tr>
            <td>${articulo.id}</td>
            <td>${articulo.nombre}</td>
            <td>${articulo.precio}</td>
            <td><img src="imagenes/articulo-${articulo.id}.jpeg" width="50" height="50" onerror="this.style.display='none'" /></td>
            <td>
              <button onclick="editarArticulo(${articulo.id}, '${articulo.nombre}', ${articulo.precio})">Editar</button>
              <button onclick="eliminarArticulo(${articulo.id})">Eliminar</button>
            </td>
          </tr>
        `;
      });
    })
    .catch(error => {
      console.error('Error cargando artículos:', error);
      alert('Error cargando artículos: ' + error.message);
    });
}

function guardarArticulo() {
  const id = document.getElementById('articulo-id').value;
  const nombre = document.getElementById('nombre').value;
  const precio = parseFloat(document.getElementById('precio').value);

  if (!nombre || isNaN(precio)) {
    alert('Por favor, ingresa un nombre y un precio válido.');
    return;
  }

  const articulo = { nombre, precio };

  const metodo = id ? 'PUT' : 'POST';
  const url = id ? `${API_URL}/${id}` : API_URL;

  fetch(url, {
    method: metodo,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(articulo)
  })
  .then(response => {
    if (!response.ok) throw new Error('Error en la respuesta: ' + response.status);
    limpiarFormulario();
    cargarArticulos();
  })
  .catch(error => {
    console.error('Error guardando artículo:', error);
    alert('Error guardando artículo: ' + error.message);
  });
}

function editarArticulo(id, nombre, precio) {
  document.getElementById('articulo-id').value = id;
  document.getElementById('nombre').value = nombre;
  document.getElementById('precio').value = precio;
}

function eliminarArticulo(id) {
  if (confirm('¿Estás seguro de eliminar este artículo?')) {
    fetch(`${API_URL}/${id}`, { method: 'DELETE' })
      .then(response => {
        if (!response.ok) throw new Error('Error en la respuesta: ' + response.status);
        cargarArticulos();
      })
      .catch(error => {
        console.error('Error eliminando artículo:', error);
        alert('Error eliminando artículo: ' + error.message);
      });
  }
}

function limpiarFormulario() {
  document.getElementById('articulo-id').value = '';
  document.getElementById('nombre').value = '';
  document.getElementById('precio').value = '';
}
