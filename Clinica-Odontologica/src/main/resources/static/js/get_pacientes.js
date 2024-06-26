// Configuración para la petición fetch y carga inicial de pacientes
window.addEventListener('load', function () {

    // Función autoejecutable para cargar pacientes
    (function(){

        const url = '/pacientes';
        const settings = {
            method: 'GET'
        };

        // Realizar la petición fetch para obtener los pacientes
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                // Recorrer los datos (array de pacientes)
                data.forEach(paciente => {
                    // Crear una nueva fila en la tabla para cada paciente
                    let pacienteRow = document.createElement('tr');
                    pacienteRow.id = `tr_${paciente.id}`;

                    // Botón para eliminar paciente
                    let deleteButton = `<button id="btn_delete_${paciente.id}" type="button" onclick="deleteBy(${paciente.id})" class="btn btn-danger btn_delete">&times;</button>`;

                    // Botón para actualizar paciente
                    let updateButton = `<button id="btn_id_${paciente.id}" type="button" onclick="findBy(${paciente.id})" class="btn btn-info btn_id">${paciente.id}</button>`;

                    // Insertar las celdas de la fila con los datos del paciente y los botones
                    pacienteRow.innerHTML = `
                        <td>${paciente.id}</td>
                        <td class="td_nombre">${paciente.nombre.toUpperCase()}</td>
                        <td class="td_apellido">${paciente.apellido.toUpperCase()}</td>
                        <td class="td_cedula">${paciente.cedula.toUpperCase()}</td>
                        <td class="td_fechaIngreso">${paciente.fechaIngreso}</td>
                        <td class="td_calle">${paciente.domicilio.calle.toUpperCase()}</td>
                        <td class="td_email">${paciente.email.toUpperCase()}</td>
                        <td>${updateButton}</td>
                        <td>${deleteButton}</td>
                    `;

                    // Agregar la fila a la tabla de pacientes
                    document.getElementById('pacienteTableBody').appendChild(pacienteRow);
                });
            })
            .catch(error => console.error('Error al cargar los pacientes:', error));
    })();

    // Añadir clase activa al enlace de la página actual en la barra de navegación
    (function() {
        let pathname = window.location.pathname;
        if (pathname === "/get_pacientes.html") {
            document.querySelector(".nav .nav-item:last-child a").classList.add("active");
        }
    })();

});

// Función para buscar y cargar los datos del paciente seleccionado para modificar
function findBy(id) {
    const url = `/pacientes/buscar/${id}`;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener el paciente');
            }
            return response.json();
        })
        .then(data => {
            // Actualizar el formulario con los datos obtenidos
            document.getElementById('paciente_id').value = data.id;
            document.getElementById('nombre').value = data.nombre;
            document.getElementById('apellido').value = data.apellido;
            document.getElementById('cedula').value = data.cedula;
            document.getElementById('fechaIngreso').value = data.fechaIngreso;
            document.getElementById('calle').value = data.domicilio.calle;
            document.getElementById('numero').value = data.domicilio.numero;
            document.getElementById('localidad').value = data.domicilio.localidad;
            document.getElementById('provincia').value = data.domicilio.provincia;
            document.getElementById('email').value = data.email;

            // Mostrar el formulario de actualización si está oculto
            document.getElementById('div_paciente_updating').style.display = 'block';
        })
        .catch(error => console.error('Error al obtener el paciente:', error));
}
