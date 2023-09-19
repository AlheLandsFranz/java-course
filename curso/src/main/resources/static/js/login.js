// Call the dataTables jQuery plugin
$(document).ready(function() {

});


async function login() {

let data = {};
data.email = document.getElementById("txtEmail").value;
data.password = document.getElementById("txtPassword").value;

const request = await fetch("api/login", {
method: "POST",
headers: {
"Accept": "application/json",
"Content-Type": "application/json"
},
body: JSON.stringify(data)
});

const response = await request.text();

if(response != "FAIL"){
localStorage.token = response;
localStorage.email = data.email;
window.location.href = "usuarios.html"
} else {
alert("Las credenciales son incorrectas. Por favor, intente nuevamente.")
}
}
