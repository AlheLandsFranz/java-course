// Call the dataTables jQuery plugin
$(document).ready(function() {

});


async function registerUser() {

let data = {};
data.name = document.getElementById("txtName").value;
data.lastname = document.getElementById("txtLastname").value;
data.email = document.getElementById("txtEmail").value;
data.phone = document.getElementById("txtPhone").value;
data.password = document.getElementById("txtPassword").value;

let repeatPassword = document.getElementById("txtRepeatPassword").value;

if(repeatPassword != data.password){
alert("La contraseña no coincide");
return;
}
const request = await fetch("api/usuarios", {
method: "POST",
headers: {
"Accept": "application/json",
"Content-Type": "application/json"
},
body: JSON.stringify(data)
});
alert("La cuenta fue creada con éxito.")
window.location.href = "login.html";
}
