function clearForm() {
    document.querySelector('#update_paciente_form').reset();
    document.getElementById('div_paciente_updating').style.display = "none";
}

function loadPacientes() {
    const url = '/pacientes';
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('pacienteTableBody');
            tableBody.innerHTML = '';
            data.forEach(paciente => {
                const pacienteRow = document.createElement('tr');
                pacienteRow.id = `tr_${paciente.id}`;
                pacienteRow.innerHTML = `
                        <td>${paciente.id}</td>
                        <td>${paciente.nombre}</td>
                        <td>${paciente.apellido}</td>
                        <td>${paciente.cedula}</td>
                        <td>${paciente.fechaIngreso}</td>
                        <td>${paciente.domicilio.calle}</td>
                        <td>${paciente.domicilio.numero}</td>
                        <td>${paciente.domicilio.localidad}</td>
                        <td>${paciente.domicilio.provincia}</td>
                        <td>${paciente.email}</td>
                        <td>
                            <button type="button" class="btn btn-danger" onclick="deleteBy(${paciente.id})">&times;</button>
                            <button type="button" class="btn btn-info btn_id" onclick="findBy(${paciente.id})">${paciente.id}</button>
                        </td>
                    `;
                tableBody.appendChild(pacienteRow);
            });
        })
        .catch(error => console.error('Error al cargar los pacientes:', error));
}