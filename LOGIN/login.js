const modalRegister = document.getElementById("modalRegister");
const modalLogin = document.getElementById("modalLogin");
const register = document.getElementById("register");
const iniciar = document.getElementById("iniciar");
const btnCerrarRegister = document.getElementById("cerrarModalRegister");
const btnCerrarLogin = document.getElementById ("cerrarModalLogin");
const cajaLogin = document.getElementById("cajaLogin");
const cajaRegister = document.getElementById("cajaRegister");


// Función para cerrar los modales 
function cerrarModal() {
    cajaLogin.style.display = "none";
    cajaRegister.style.display = "none"
    // Limpiar los textos de los modales
    document.getElementById("aceptado").innerText = "";
    document.getElementById("bienvenida").innerText = "";
}

// Aplicamos la función "cerrarModal" al pulsar en el botón del modal ("x")
btnCerrarRegister.addEventListener("click", function() {
    cerrarModal();
});
btnCerrarLogin.addEventListener("click", function(){
    cerrarModal();
})


// REGISTRO
register.addEventListener("click", function() {
    // Obtenemos los valores del forumlario de registro
    const usuario = document.getElementById('regUsuario').value.trim();
    const correo = document.getElementById('regCorreo').value.trim();
    const contrasena = document.getElementById('regPassword').value.trim();
    // Definimos los datos para enviarlos al backend
    const data = { usuario, correo, contrasena };
    const JsonEnv = JSON.stringify(data);
    // Enviamos una solicitud "POST" al endpoint del registro
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
        
        if (mensaje.includes("error")) {
            
            // Limpiamos los campos del formulario
            document.getElementById('regUsuario').value = "";
            document.getElementById('regCorreo').value = "";
            document.getElementById('regPassword').value = "";
            
            // NO redirigir
            return;
        } else {
            // Limpiamos los campos y redirigimos al inicio (registro correcto)
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
    // Obtenemos los valores del formulario del Login
    const usuario = document.getElementById('logUsuario').value.trim();
    const contrasena = document.getElementById('logPassword').value.trim();
    // Definimos los datos para enviarlos al backend
    const dato = { usuario, contrasena };
    const JsonCom = JSON.stringify(dato);
    // Enviamos una solicitud "POST" al endpoint del Login
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
            // Limpiamos los campos del formulario
            document.getElementById('logUsuario').value = "";
            document.getElementById('logPassword').value = "";
            
            // NO redirigir
            return;
        } else {
            // Limpiamos los campos y redirigimos al inicio (login correcto)
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
