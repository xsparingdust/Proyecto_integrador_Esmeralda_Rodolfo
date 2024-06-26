window.addEventListener('load', function () {
    const url = '/turnos'; // URL base de la API para los turnos

    // Función para cargar y mostrar los turnos desde la API
    function cargarTurnos() {
        fetch(url + '/buscarTodos')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud.');
                }
                return response.json();
            })
            .then(data => {
                // Limpiar tabla antes de agregar nuevos datos
                const tableBody = document.getElementById('turnosTableBody');
                tableBody.innerHTML = '';

                // Iterar sobre los datos y agregar filas a la tabla
                data.forEach(turno => {
                    const row = tableBody.insertRow();
                    row.innerHTML = `
                        <td>${turno.id}</td>
                        <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                        <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                        <td>${new Date(turno.fecha).toLocaleString()}</td>
                        <td>
                            <button type="button" class="btn btn-info btn-sm editar-turno" data-toggle="modal" data-target="#editTurnoModal" data-id="${turno.id}">Editar</button>
                        </td>
                    `;
                });

                // Agregar evento para editar turno
                const editButtons = document.querySelectorAll('.editar-turno');
                editButtons.forEach(btn => {
                    btn.addEventListener('click', function () {
                        const turnoId = this.getAttribute('data-id');
                        cargarDatosTurnoParaEdicion(turnoId);
                    });
                });
            })
            .catch(error => {
                console.error('Error al obtener los turnos:', error);
                // Mostrar mensaje de error en caso de fallo
                const tableBody = document.getElementById('turnosTableBody');
                tableBody.innerHTML = '<tr><td colspan="5">Error al cargar los turnos.</td></tr>';
            });
    }

    // Función para cargar datos de un turno específico para editar
    function cargarDatosTurnoParaEdicion(turnoId) {
        fetch(`${url}/${turnoId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud.');
                }
                return response.json();
            })
            .then(turno => {
                // Llenar el modal con los datos del turno seleccionado
                document.getElementById('editTurnoId').value = turno.id;
                document.getElementById('editPaciente').value = turno.paciente.id;
                document.getElementById('editOdontologo').value = turno.odontologo.id;
                // Formatear la fecha para el input tipo datetime-local
                const fechaFormateada = new Date(turno.fecha).toISOString().slice(0, 16);
                document.getElementById('editFecha').value = fechaFormateada;
            })
            .catch(error => {
                console.error('Error al cargar datos del turno para editar:', error);
            });
    }

    // Llamar a la función para cargar los turnos cuando se carga la página
    cargarTurnos();

    // Manejar el envío del formulario de edición de turno
    const editTurnoForm = document.getElementById('edit_turno_form');
    editTurnoForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const turnoId = document.getElementById('editTurnoId').value;
        const pacienteId = document.getElementById('editPaciente').value;
        const odontologoId = document.getElementById('editOdontologo').value;
        const fecha = document.getElementById('editFecha').value;

        const data = {
            id: turnoId,
            paciente: { id: pacienteId },
            odontologo: { id: odontologoId },
            fecha: fecha
        };

        fetch(`${url}/${turnoId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al actualizar el turno.');
                }
                return response.json();
            })
            .then(data => {
                // Mostrar mensaje de éxito o actualizar la lista de turnos
                document.getElementById('response').innerHTML = '<div class="alert alert-success" role="alert">Turno actualizado correctamente.</div>';
                document.getElementById('response').style.display = 'block';

                // Volver a cargar los turnos después de la actualización
                cargarTurnos();
            })
            .catch(error => {
                console.error('Error al actualizar el turno:', error);
                document.getElementById('response').innerHTML = '<div class="alert alert-danger" role="alert">Error al actualizar el turno.</div>';
                document.getElementById('response').style.display = 'block';
            });
    });
});