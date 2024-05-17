document.addEventListener('DOMContentLoaded', function () {
    const btnAsignarCalif = document.getElementById('btn-asignar-calif');
    const inputCalificacion = document.getElementById('input-calificacion');

    btnAsignarCalif.addEventListener('click', function () {
        const calificacion = inputCalificacion.value.trim();

        if (!calificacion) {
            console.error('Error: La calificación está vacía');
            return;
        }

        const data = {
            calificacion: calificacion,
            idAlumno: 1,
            idAsignacion: 3
        };

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        fetch('http://localhost:8080/subircalif', requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud POST');
                }
                alert("Se asignó correctamente la calificación")
                return response.json();
            })
            .then(data => {
                console.log('Calificación enviada con éxito:', data);
            })
            .catch(error => {
                console.error('Error en la solicitud POST:', error);
            });
    });
});
