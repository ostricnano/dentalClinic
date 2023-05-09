window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del turno
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;

        //creamos un JSON que tendrá los datos del turno
        //a diferencia de un turno nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#turno_id').value,
            odontologo_id: document.querySelector('#odontologo_id').value,
            paciente_id: document.querySelector('#paciente_id').value,
            fecha: document.querySelector('#turno_fecha').value,
            nombre_odontologo: document.querySelector('#nombre_odontologo').value,
            nombre_paciente: document.querySelector('#nombre_paciente').value,
        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/turnos/actualizar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())
    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
    console.log("bandera")
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#odontologo_id').value = turno.odontologo_id;
              document.querySelector('#nombre_odontologo').value = turno.nombre_odontologo;
              document.querySelector('#paciente_id').value = turno.paciente_id ;
              document.querySelector('#nombre_paciente').value = turno.nombre_paciente;
              document.querySelector('#turno_fecha').value = turno.fecha;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }