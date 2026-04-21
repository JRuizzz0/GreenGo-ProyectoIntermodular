const myHeaders = new Headers();

const requestOptions = {
  method: "GET",
  headers: myHeaders,
  redirect: "follow"
};

const registrar = document.getElementById("registro");

registrar.addEventListener("click", function() {
  const data = {
    usuario: document.getElementById('regUsuario').value.trim(),
    correo: document.getElementById('regCorreo').value.trim(),
    contrasena: document.getElementById('regPassword').value.trim()
  };

  const JsonEnv = JSON.stringify(data);
  
  fetch("http://localhost:8081/registro", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JsonEnv
  })
  .then(response => response.json())
  .then(result => {
    console.log(JsonEnv);
  })
  .catch(error => console.error("Error:", error));
});

const iniciar = document.getElementById("inicio");

iniciar.addEventListener("click", function() {
  const dato = {
    usuario: document.getElementById('logUsuario').value.trim(),
    contrasena: document.getElementById('logPassword').value.trim()
  };

  const JsonCom = JSON.stringify(dato);

  fetch("http://localhost:8081/login", {
    method: "POST",
    headers:{
      "Content-Type": "application/json"
    },
    body: JsonCom
  })
  .then(response => response.json())
  .then(result => {
    console.log(JsonCom);
  })
  .catch(error => console.error("Error", error));
})