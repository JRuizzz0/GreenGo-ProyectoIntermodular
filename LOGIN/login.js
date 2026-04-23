const modalRegister = document.getElementById("modalRegister");
const modalLogin = document.getElementById("modalLogin");
const register = document.getElementById("register");
const iniciar = document.getElementById("iniciar");
const btnCerrarRegister = document.getElementById("cerrarModalRegister");
const btnCerrarLogin = document.getElementById ("cerrarModalLogin");
const cajaLogin = document.getElementById("cajaLogin");
const cajaRegister = document.getElementById("cajaRegister");



function cerrarModal() {
    cajaLogin.style.display = "none";
    cajaRegister.style.display = "none"
    // Limpiar los textos de los modales
    document.getElementById("aceptado").innerText = "";
    document.getElementById("bienvenida").innerText = "";
}
btnCerrarRegister.addEventListener("click", function() {
    cerrarModal();
});
btnCerrarLogin.addEventListener("click", function(){
    cerrarModal();
})


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
    .then(async response => {
    if (!response.ok) {
        throw new Error(data.error || 'Error en la petición');
    }
    return response.json();
})
.then(data => {
    console.log('Mensaje:', data);
    cajaRegister.style.display = "flex";
    document.getElementById("aceptado").innerText = data.recibido;

    // Limpiar campos
    document.getElementById('regUsuario').value = "";
    document.getElementById('regCorreo').value = "";
    document.getElementById('regPassword').value = "";

    window.location.href="../Inicio/inicio.html"
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
    .then(async response => {
    if (!response.ok) {
        throw new Error(dato.error || 'Error en la petición');
    }
    return response.json();
})
    .then(dato => {
        console.log('Mensaje: ' , dato)
        cajaLogin.style.display = "flex";
        document.getElementById("bienvenida").innerText = dato.recibido;
        
        
        // Limpiar campos
        document.getElementById('logUsuario').value = "";
        document.getElementById('logPassword').value = "";
        
        window.location.href="../Inicio/inicio.html"

    })
    .catch(error => {
        console.error("Error:", error);
    });
});
