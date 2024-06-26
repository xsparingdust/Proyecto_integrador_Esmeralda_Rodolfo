window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();


        const formData = {
            numeroMatricula: document.querySelector('#numeroMatricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value
        };


        const url = '/odontologos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar el odontólogo');
                }
                return response.json();
            })
            .then(data => {

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Odontólogo agregado</strong>' +
                    '</div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                // Si hay algún error, mostramos un mensaje de error
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong>' +
                    '</div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            });
    });

    function resetUploadForm() {
        document.querySelector('#numeroMatricula').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
    }

    // Función para marcar el enlace activo en la barra de navegación
    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item:first-child a").classList.add("active");
        } else if (pathname === "/post_odontologos.html") {
            document.querySelector(".nav .nav-item:last-child a").classList.add("active");
        }
    })();

});