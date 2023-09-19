// Call the dataTables jQuery plugin
$(document).ready(function() {
cargarUsuarios();
  $('#usuarios').DataTable();
});

function getHeaders(){
return {
       "Accept": "application/json",
       "Content-Type": "application/json",
       "Authorization": localStorage.token
       }
}

async function cargarUsuarios() {
const request = await fetch("api/usuarios", {
method: "GET",
headers: getHeaders()
});

const usuarios = await request.json();

let listadoHtml = "";
for(let usuario of usuarios){

let deleteBtn = '<a href="#" onclick="deleteUser(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

let txtPhone = usuario.phone == null ? '-' : usuario.phone;
    let usuarioHtml = '<tr><td>'+usuario.id +'</td><td>'
    + usuario.name + ' ' + usuario.lastname +'</td><td>'
    + usuario.email+'</td><td>'+ txtPhone
    + '</td><td>' + deleteBtn + '</td></tr>';

    listadoHtml += usuarioHtml;
}
document.querySelector("#usuarios tbody").outerHTML = listadoHtml;

}

async function deleteUser(id){

if(!confirm("Desea eliminar este usuario?")){
return;
}
await fetch("api/usuario/"+id, {
method: "DELETE",
headers: getHeaders()
});

location.reload();
}
