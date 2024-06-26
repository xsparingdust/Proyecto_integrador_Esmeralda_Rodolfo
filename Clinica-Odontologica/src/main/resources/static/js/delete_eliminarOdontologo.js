// Contenido de delete_eliminarOdontologo.js
function deleteBy(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                const row = document.getElementById(`tr_${id}`);
                if (row) {
                    row.remove();
                }
                clearForm();
            } else {
                console.error('Error al eliminar el odontólogo');
            }
        })
        .catch(error => console.error('Error al eliminar el odontólogo:', error));
}
