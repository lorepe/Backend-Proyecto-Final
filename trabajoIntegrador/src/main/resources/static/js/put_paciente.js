window.addEventListener('load', function () {

    
    const formulario = document.querySelector('#update_paciente_form');
    formulario.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_id').value;

        const idInput= document.querySelector('#domicilio_id').value;
        const calleInput= document.querySelector('#calle').value;
        const numeroInput= document.querySelector('#numero').value;
        const localidadInput= document.querySelector('#localidad').value;
        const provinciaInput= document.querySelector('#provincia').value;
        
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
                id:idInput,
                calle: calleInput,
                numero: numeroInput,
                localidad: localidadInput,
                provincia: provinciaInput
            }

        };
        console.log(formData)

        
        const url = '/pacientes';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())
          .then(data=> console.log(data))

    })
 })

    
    function findBy(id) {
          const url = '/pacientes'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              console.log(paciente)

              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#nombre').value = paciente.nombre;
              document.querySelector('#apellido').value = paciente.apellido;
              document.querySelector('#dni').value = paciente.dni;
              document.querySelector('#domicilio_id').value = paciente.domicilio.id;
              document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
              document.querySelector('#calle').value = paciente.domicilio.calle;
              document.querySelector('#numero').value = paciente.domicilio.numero;
              document.querySelector('#localidad').value = paciente.domicilio.localidad;
              document.querySelector('#provincia').value=paciente.domicilio.provincia;

            console.log(paciente)
            
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              console.log(error)
              alert("Error: " + error);
          })
      }