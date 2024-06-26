function findBy(id) {
    const url = `/odontologos/buscar/${id}`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            // Actualizar el formulario o cualquier otro elemento con los datos obtenidos
            document.getElementById('odontologo_id').value = data.id;
            document.getElementById('numeroMatricula').value = data.numeroMatricula;
            document.getElementById('nombre').value = data.nombre;
            document.getElementById('apellido').value = data.apellido;

            // Mostrar el formulario de actualización si está oculto
            document.getElementById('div_odontologo_updating').style.display = 'block';
        })
        .catch(error => console.error('Error al obtener el odontólogo:', error));
}

function loadOdontologos() {
    fetch('/odontologos')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('odontologoTableBody');
            tbody.innerHTML = '';
            data.forEach(odontologo => {
                const tr = document.createElement('tr');
                tr.id = `tr_${odontologo.id}`;
                tr.innerHTML = `
                    <td>${odontologo.id}</td>
                    <td>${odontologo.numeroMatricula}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.apellido}</td>
                    <td>
                        <button class="btn btn-danger" onclick="deleteBy(${odontologo.id})">X</button>
                        <button class="btn btn-primary" onclick="findBy(${odontologo.id})">✏️</button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error al cargar los odontólogos:', error));
}

// Evento para cargar los odontólogos al cargar el DOM
document.addEventListener('DOMContentLoaded', (event) => {
    loadOdontologos();
});
