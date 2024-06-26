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

