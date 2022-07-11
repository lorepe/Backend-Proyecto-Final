window.addEventListener('load', function () {

     
    const formulario = document.querySelector('#add_new_turno');

    
    formulario.addEventListener('submit', function (event) {

        
        const pacientes = document.querySelector('#paciente_id').value
        const odontologos = document.querySelector('#odontologo_id').value
        console.log(pacientes, odontologos);
        const formData = {
            paciente: {id: pacientes},
            odontologo: {id: odontologos},
            fecha: document.querySelector('#fecha').value
        };

        
        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
            console.log(data)
               
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Estudiante agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 
                 resetUploadForm();

            })
            .catch(error => {
                console.log(error)
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";
                   
                   resetUploadForm();})
    });

    function resetUploadForm(){
        document.querySelector('#paciente_id').value = "";
        document.querySelector('#odontologo_id').value = "";
        document.querySelector('#fecha').value="";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});