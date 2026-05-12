const contTarjetas = document.getElementById("tarjetas");
const inputBusqueda = document.getElementById("inpTexto");
const btnBusqueda = document.getElementById("btnBusqueda");
const selectorCategorias = document.getElementById("slcPlatos");
const btnVolver = document.getElementById("volver");

let productosOriginales = [];
let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

// Aquí obtenemos los productos desde el backend
async function obtenerProductos() {
    try {
        const respuesta = await fetch('http://localhost:8080/api/productos');
        productosOriginales = await respuesta.json();
        cargarProductos(productosOriginales);
        actualizarContador();
    } catch (error) {
        console.error(error);
    }
}

// Función para renderizar las tarjetas de productos en el contenedor
function cargarProductos(lista) {
    contTarjetas.innerHTML = "";
    
    lista.forEach(p => {
        contTarjetas.innerHTML += `
            <div class="tarjeta" onclick="abrirDetallePlato(${p.id})" style="cursor: pointer;">
                <img src="${p.imagenUrl}" alt="${p.nombre}" class="imagen-plato">
                <div class="titulo">${p.nombre}</div>
                <p class="descripcion">${p.descripcion}</p>
                <div class="precio-seccion">
                    ${(p.precioBase * 1.1).toFixed(2)}€ <small>(IVA inc.)</small>
                </div>
                <button onclick="event.stopPropagation(); comprar(${p.id})" class="btn-comprar">
                    Añadir al carrito
                </button>
            </div>
        `;
    });
}

// Función para mostrar el modal con los detalles completos del plato seleccionado
function abrirDetallePlato(id) {
    const producto = productosOriginales.find(p => p.id === id);
    
    if (producto) {
        document.getElementById("nombrePlato").innerText = producto.nombre;
        document.getElementById("imgPlato").innerHTML = `<img src="${producto.imagenUrl}" alt="${producto.nombre}">`;
        
        const textoAlergenos = producto.alergeno && producto.alergeno.nombre ? producto.alergeno.nombre : "Sin alérgenos";
        document.getElementById("nombreAlergeno").innerText = `Contiene: ${textoAlergenos}`;
        
        document.getElementById("descAlergeno").innerText = producto.alergeno && producto.alergeno.descripcion ? producto.alergeno.descripcion : "";

        document.getElementById("modalPlato").style.display = "flex";
    }
}

// Función para mostrar la notificación de añadir al carrito
function mostrarNotificacion(mensaje) {
    const noti = document.getElementById("notificacion");
    noti.innerText = mensaje;
    noti.className = "notificacion-visible";
    
    setTimeout(() => {
        noti.className = "notificacion-oculta";
    }, 3000);
}

// Función para agregar el producto al carrito
function comprar(id) {
    const producto = productosOriginales.find(p => p.id === id);
    const existe = carrito.find(item => item.id === id);

    if (existe) {
        existe.cantidad++;
    } else {
        carrito.push({
            ...producto,
            cantidad: 1
        });
    }

    guardarCarrito();
    mostrarNotificacion(`${producto.nombre} añadido`);
}

// Función para guardar el carrito en localStorage y actualiza el contador
function guardarCarrito() {
    localStorage.setItem("carrito", JSON.stringify(carrito));
    actualizarContador();
}

// Función para actualizar el contador de productos del carrito
function actualizarContador() {
    const contador = document.getElementById("contador-carrito");
    if (contador) {
        const totalProductos = carrito.reduce((total, item) => total + item.cantidad, 0);
        contador.innerText = totalProductos;
    }
}

// Abrimos el modal del carrito al hacer click en el botón
document.getElementById("btnCarrito").addEventListener("click", () => {
    document.getElementById("modalTarjeta").style.display = "flex";
    mostrarCarrito();
});

// Función para renderizar el contenido actual del carrito dentro del modal
function mostrarCarrito() {
    const contenedorItems = document.getElementById("contenedorItemsCarrito");
    
    if (carrito.length === 0) {
        contenedorItems.innerHTML = `
            <h3>Tu carrito está vacío 🛒</h3>
            <br>
            <button class="btn-cerrar">Volver a la tienda</button>`;
        return;
    }

    let html = `
        <h3>Tu Pedido</h3><br>`;
        
    let totalPagar = 0;

    carrito.forEach(item => {
        const subtotal = (item.precioBase * 1.1) * item.cantidad;
        totalPagar += subtotal;
        
        html += `
            <div class="item-carrito" style="display:flex; justify-content:space-between; align-items:center; margin-bottom:10px;">
                <div style="flex-grow: 1; text-align: left;">
                    <p><strong>${item.nombre}</strong></p>
                    <p style="font-size: 0.9em; color: #666;">Cant: ${item.cantidad} x ${(item.precioBase * 1.1).toFixed(2)}€</p>
                </div>
                <div style="display:flex; align-items:center; gap: 15px;">
                    <p style="font-weight:bold;">${subtotal.toFixed(2)}€</p>
                    <button class="btn-eliminar-item" data-id="${item.id}" title="Quitar plato">🗑️</button>
                </div>
            </div>
            <hr style="opacity:0.2; margin-bottom:10px;">`;
    });

    html += `<h4 style="text-align:right; margin-top:15px;">Total: ${totalPagar.toFixed(2)}€</h4>`;
    html += `<button class="btn-pagar">Finalizar Compra</button>`;
    html += `<button class="btn-borrar">Vaciar todo el Carrito</button>`;
    
    contenedorItems.innerHTML = html;
}

// Función para cerrar los modales
function cerrarTodosLosModales() {
    document.querySelectorAll(".modal").forEach(modal => {
        modal.style.display = "none";
    });
}

// Controla todas las acciones del carrito: vaciar, quitar producto y proceder al pago
document.addEventListener("click", (e) => {
    if (typeof e.target.className === 'string' && e.target.className.includes("cerrar")) {
        cerrarTodosLosModales();
    }
    
    if (e.target.classList.contains("modal")) {
        cerrarTodosLosModales();
    }

    if (e.target.classList.contains("btn-borrar")) {
        carrito = [];
        guardarCarrito();
        cerrarTodosLosModales();
        mostrarNotificacion("Carrito vaciado 🗑️");
    }
    
    if (e.target.classList.contains("btn-eliminar-item")) {
        
        const idProducto = parseInt(e.target.getAttribute("data-id"));
        carrito = carrito.filter(item => item.id !== idProducto);
        guardarCarrito();
        mostrarCarrito(); 
    }
    
    if (e.target.classList.contains("btn-pagar")) {
        if (carrito.length > 0) {
            document.getElementById("modalTarjeta").style.display = "none";
            
            const total = carrito.reduce((acc, item) => acc + (item.precioBase * 1.1 * item.cantidad), 0);
            document.getElementById("totalFinal").innerText = `Total a pagar: ${total.toFixed(2)}€`;
            
            document.getElementById("modalPago").style.display = "flex";
        }
    }
});

// Envía el pedido al backend 
document.getElementById("formPago").addEventListener("submit", async (e) => {
    e.preventDefault();

    const datosDelPedido = {
        nombreCliente: document.getElementById("nombreCliente").value,
        direccion: document.getElementById("direccion").value,
        total: carrito.reduce((sum, item) => sum + (item.precioBase * 1.1 * item.cantidad), 0),
        lineas: carrito.map(item => ({
            idProducto: item.id,
            cantidad: item.cantidad,
            precioUnitario: item.precioBase * 1.1
        }))
    };

    try {
        const respuesta = await fetch('http://localhost:8080/api/pedidos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(datosDelPedido)
        });

        if (respuesta.ok) {
            carrito = [];
            guardarCarrito();
            cerrarTodosLosModales();
            document.getElementById("formPago").reset(); 
            mostrarNotificacion("¡Pedido enviado con éxito!" );
        } else {
            mostrarNotificacion("Hubo un error al procesar tu pedido.");
        }
    } catch (error) {
        mostrarNotificacion("Error de conexión con el servidor.");
    }
});

// Busca y filtra platos por nombre según lo que el usuario escribe en el campo de búsqueda
btnBusqueda.addEventListener("click", () => {
    const textoBuscado = inputBusqueda.value.toLowerCase();
    const tarjetasCargadas = document.querySelectorAll(".tarjeta");

    tarjetasCargadas.forEach(tarjeta => {
        const tituloTarjeta = tarjeta.querySelector(".titulo").textContent.toLowerCase();

        if (tituloTarjeta.includes(textoBuscado)) {
            tarjeta.style.display = "flex";
        } else {
            tarjeta.style.display = "none";
        }
    });
});

// Funcionalidad del botón "volver" para que rediriga al login
btnVolver.addEventListener("click", ()=>{
    window.location.href = "../LOGIN/login.html"
})

// Filtramos los productos por categoría seleccionada
selectorCategorias.addEventListener("change", () => {
    const idSeleccionado = parseInt(selectorCategorias.value);

    if (idSeleccionado === 0 || isNaN(idSeleccionado)) {
        cargarProductos(productosOriginales);
    } else {
        const filtrados = productosOriginales.filter(p => p.categoria && p.categoria.id === idSeleccionado);
        cargarProductos(filtrados);
    }
});

//Inicializamos la aplicación
obtenerProductos();