const modalRegister = document.getElementById("modalRegister");
const modalLogin = document.getElementById("modalLogin");
const register = document.getElementById("register");
const iniciar = document.getElementById("iniciar");
const btnCerrar = document.getElementById("cerrarModal");


function cerrarModal() {
    modalLogin.style.display = "none";
    modalRegister.style.display = "none"
    // Limpiar los textos de los modales
    document.getElementById("aceptado").innerText = "";
    document.getElementById("bienvenida").innerText = "";
}
btnCerrar.addEventListener("click", function() {
    cerrarModal();
});


// REGISTRO
register.addEventListener("click", function() {
    const usuario = document.getElementById('regUsuario').value.trim();
    const correo = document.getElementById('regCorreo').value.trim();
    const contrasena = document.getElementById('regPassword').value.trim();

    const data = { usuario, correo, contrasena };
    const JsonEnv = JSON.stringify(data);

    fetch("http://localhost:8080/registro", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JsonEnv
    })
    .then(response => response.json())
    .then(result => {
      modalRegister.style.display = "flex";
      document.getElementById("aceptado").innerText = `¡Bienvenido ${usuario}! Se ha registrado correctamente`;
        
        
        // Limpiar campos
        document.getElementById('regUsuario').value = "";
        document.getElementById('regCorreo').value = "";
        document.getElementById('regPassword').value = "";
        
    })
    .catch(error => {
        console.error("Error:", error);
    });
});

// INICIO DE SESIÓN
iniciar.addEventListener("click", function() {
    const usuario = document.getElementById('logUsuario').value.trim();
    const contrasena = document.getElementById('logPassword').value.trim();

    if (!usuario || !contrasena) {
        return;
    }

    const dato = { usuario, contrasena };
    const JsonCom = JSON.stringify(dato);

    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JsonCom
    })
    .then(response => response.json())
    .then(result => {
        modalLogin.style.display = "flex";
        document.getElementById("bienvenida").innerText = `¡Hola de nuevo ${usuario}! Has iniciado sesión`;
        
        
        // Limpiar campos
        document.getElementById('logUsuario').value = "";
        document.getElementById('logPassword').value = "";
      
    })
    .catch(error => {
        console.error("Error:", error);
    });
});
