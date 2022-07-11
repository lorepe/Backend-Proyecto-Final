window.addEventListener('load', function () {

    
   const formulario = document.querySelector('#user');

 
   formulario.addEventListener('submit', function (event) {

       
       const formData = {
           username: document.querySelector('#username').value,
           password: document.querySelector('#password').value
       };
    
       
       const url = '/authenticate';
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
                    '<strong></strong> usuario logueado </div>'

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
       document.querySelector('#username').value = "";
       document.querySelector('#password').value = "";
     
   }

   (function(){
       let pathname = window.location.pathname;
       if(pathname === "/"){
           document.querySelector(".nav .nav-item a:first").addClass("active");
       } else if (pathname == "/index.html") {
           document.querySelector(".nav .nav-item a:last").addClass("active");
       }
   })();
});