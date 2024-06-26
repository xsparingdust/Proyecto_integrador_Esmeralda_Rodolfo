document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('update_paciente_form');

    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // Evitar que el formulario se envíe

            const id = document.getElementById('paciente_id').value;
            const nombre = document.getElementById('nombre').value;
            const apellido = document.getElementById('apellido').value;
            const cedula = document.getElementById('cedula').value;
            const fechaIngreso = document.getElementById('fechaIngreso').value;
            const calle = document.getElementById('calle').value;
            const numero = document.getElementById('numero').value;
            const localidad = document.getElementById('localidad').value;
            const provincia = document.getElementById('provincia').value;
            const email = document.getElementById('email').value;

            const pacienteData = {
                id: id,
                nombre: nombre,
                apellido: apellido,
                cedula: cedula,
                fechaIngreso: fechaIngreso,
                domicilio: {
                    calle: calle,
                    numero: numero,
                    localidad: localidad,
                    provincia: provincia
                },
                email: email
            };

            fetch(`/pacientes/update`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(pacienteData)
            })
            .then(response => {
                if (response.ok) {
                    console.log('Paciente actualizado correctamente');
                    clearForm(); // Limpia el formulario después de la actualización
                    loadPacientes(); // Vuelve a cargar la lista de pacientes actualizada
                } else {
                    console.error('Error al actualizar el paciente');
                    response.json().then(data => console.error(data)); // Verifica el mensaje de error específico
                }
            })
            .catch(error => console.error('Error en la solicitud PUT:', error));
        });
    } else {
        console.error('No se encontró el formulario de actualización en el DOM.');
    }
});
