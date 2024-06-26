window.addEventListener('load', function () {
    document.getElementById('add_new_turno').addEventListener('submit', function (event) {
        event.preventDefault();

        // Obtener los datos del formulario
        const pacienteId = document.getElementById('paciente').value;
        const odontologoId = document.getElementById('odontologo').value;
        const fecha = document.getElementById('fecha').value;

        // Crear objeto de datos para enviar al servidor
        const data = {
            paciente: { id: pacienteId },
            odontologo: { id: odontologoId },
            fecha: fecha
        };

        // Configurar la solicitud POST con Fetch API
        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        // Realizar la solicitud al servidor
        fetch(url, settings)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Error en la solicitud POST');
            })
            .then(data => {
                // Mostrar mensaje de éxito al usuario
                document.getElementById('response').innerHTML = '<div class="alert alert-success" role="alert">Turno guardado correctamente.</div>';
                document.getElementById('response').style.display = 'block';

                // Limpiar el formulario después de guardar
                document.getElementById('add_new_turno').reset();
            })
            .catch(error => {
                // Mostrar mensaje de error al usuario
                document.getElementById('response').innerHTML = '<div class="alert alert-danger" role="alert">Error al guardar el turno.</div>';
                document.getElementById('response').style.display = 'block';
                console.error('Error en la solicitud:', error);
            });
    });
});
