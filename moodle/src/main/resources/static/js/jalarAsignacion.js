document.addEventListener('DOMContentLoaded', function () {
    const tareasContainer = document.getElementById('tareas-container');

    fetch('http://localhost:8080/consultarAsig?idAsignacion=1')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud GET');
            }
            return response.json();
        })
        .then(data => {
            if (typeof data === 'object' && data.hasOwnProperty('id')) {
                // Limpiar el contenedor antes de agregar la tarea
                tareasContainer.innerHTML = '';

                // Agregar la tarea al contenedor
                const tareaElement = document.createElement('div');
                tareaElement.classList.add('tarea');
                tareaElement.innerHTML = `
                    <h3>${data.nombre}</h3>
                    <p>Curso: ${data.idCurso}</p>
                    <p>Descripción de la tarea.</p>
                    <p>Fecha de entrega: 10/04/2024</p>
                    <button class="btn-tarea" onclick="entregarTarea(${data.id})">Entregar Tarea</button>
                `;
                tareasContainer.appendChild(tareaElement);
            } else {
                console.error('La respuesta no es una tarea válida:', data);
            }
        })
        .catch(error => {
            console.error('Error en la solicitud GET:', error);
        });

    // Función para entregar una tarea
    function entregarTarea(idTarea) {
        // Lógica para entregar la tarea
        console.log('Entregar tarea con ID:', idTarea);
    }
});
