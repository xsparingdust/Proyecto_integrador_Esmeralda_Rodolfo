// Función para eliminar un paciente por su ID
function deleteBy(id) {
    const url = `/pacientes/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                document.getElementById(`tr_${id}`).remove();
                console.log(`Paciente con id ${id} eliminado`);
            } else {
                console.error('Error al eliminar el paciente');
            }
        })
        .catch(error => console.error('Error al eliminar el paciente:', error));
}

// Función para encontrar un paciente por su ID y cargar sus datos en el formulario de actualización
function findBy(id) {
    const url = `/pacientes/${id}`;
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            document.querySelector('#pelicula_id').value = data.id;
            document.querySelector('#nombre').value = data.nombre;
            document.querySelector('#apellido').value = data.apellido;
            document.querySelector('#cedula').value = data.cedula;
            document.querySelector('#fechaIngreso').value = data.fechaIngreso;
            document.querySelector('#calle').value = data.domicilio.calle;
            document.querySelector('#numero').value = data.domicilio.numero;
            document.querySelector('#localidad').value = data.domicilio.localidad;
            document.querySelector('#provincia').value = data.domicilio.provincia;
            document.querySelector('#email').value = data.email;

            document.getElementById('div_paciente_updating').style.display = "block";
        })
        .catch(error => console.error('Error al obtener el paciente:', error));
}
