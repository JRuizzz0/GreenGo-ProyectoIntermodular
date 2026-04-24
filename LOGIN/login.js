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
    .then(response => response.json())
    .then(data => {
        console.log('Respuesta del backend:', data);
        
        const mensaje = data.recibido;
        cajaRegister.style.display = "flex";
        const modalText = document.getElementById("aceptado");
        modalText.innerText = mensaje;
        
        if (mensaje.includes("inválidos") || mensaje.includes("error")) {
            
            // Limpiar campos del formulario
            document.getElementById('regUsuario').value = "";
            document.getElementById('regCorreo').value = "";
            document.getElementById('regPassword').value = "";
            
            // NO redirigir
            return;
        } else {
            // Limpiar campos
            document.getElementById('regUsuario').value = "";
            document.getElementById('regCorreo').value = "";
            document.getElementById('regPassword').value = "";
            
            // Aquí se redirige después de 2 segundos
            setTimeout(() => {
                window.location.href = "../Inicio/inicio.html";
            }, 2000);
        }
    })
    .catch(error => {
        console.error("Error en la petición:", error);
        cajaRegister.style.display = "flex";
        const modalText = document.getElementById("aceptado");
        modalText.innerText = "Error de conexión con el servidor";
    });
});
// INICIO DE SESIÓN
iniciar.addEventListener("click", function() {
    const usuario = document.getElementById('logUsuario').value.trim();
    const contrasena = document.getElementById('logPassword').value.trim();

    const dato = { usuario, contrasena };
    const JsonCom = JSON.stringify(dato);

    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JsonCom
    })
    .then(response => response.json())
    .then(data => {
        console.log('Respuesta del backend:', data);
        
        const mensaje = data.recibido;
        
        cajaLogin.style.display = "flex";
        const modalText = document.getElementById("bienvenida");
        modalText.innerText = mensaje;
        
        if (mensaje.includes("incorrecta") || mensaje.includes("error")) {
            // Limpiar campos del formulario
            document.getElementById('logUsuario').value = "";
            document.getElementById('logPassword').value = "";
            
            return;
        } else {
            // Limpiar campos
            document.getElementById('logUsuario').value = "";
            document.getElementById('logPassword').value = "";

            
            // Aquí se redirige después de 2 segundos
            setTimeout(() => {
                window.location.href = "../Inicio/inicio.html";
            }, 2000);
        }
    })
    .catch(error => {
        console.error("Error en la petición:", error);
        cajaLogin.style.display = "flex";
        const modalText = document.getElementById("bienvenida");
        modalText.innerText = "Error de conexión con el servidor";
    });
});
