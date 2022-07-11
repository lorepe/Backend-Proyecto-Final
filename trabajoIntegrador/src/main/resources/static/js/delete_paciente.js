function deleteBy(id)
{
           
          const url = '/pacientes/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch('/pacientes/'+ id,settings)
          .then(response => response.json())

          
          let row_id = "#tr_" + id;
          document.querySelector(row_id).remove();

}