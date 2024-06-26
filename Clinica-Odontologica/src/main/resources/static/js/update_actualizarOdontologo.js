document.addEventListener('DOMContentLoaded', function() {
    const actualizarButton = document.getElementById('actualizar_button');

    if (actualizarButton) {
        actualizarButton.addEventListener('click', function(event) {
            event.preventDefault();

            const id = document.getElementById('odontologo_id').value;
            const numeroMatricula = document.getElementById('numeroMatricula').value;
            const nombre = document.getElementById('nombre').value;
            const apellido = document.getElementById('apellido').value;

            const odontologoData = {
                id: id,
                numeroMatricula: numeroMatricula,
                nombre: nombre,
                apellido: apellido
            };

            fetch(`/odontologos/update`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(odontologoData)
            })
            .then(response => {
                if (response.ok) {
                    console.log('Odontólogo actualizado correctamente');
                    clearForm();
                    loadOdontologos();
                } else {
                    console.error('Error al actualizar el odontólogo');
                    response.json().then(data => console.error(data)); // Verifica el mensaje de error específico
                }
            })
            .catch(error => console.error('Error en la solicitud PUT:', error));
        });
    } else {
        console.error('No se encontró el botón de actualizar en el DOM.');
    }
});

