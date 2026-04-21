
const contTarjetas = document.getElementById("tarjetas");
const inputBusqueda = document.getElementById("inpTexto");
const btnBusqueda = document.getElementById("btnBusqueda");
const header = document.querySelector(".header");
const main = document.querySelector("main");
const footer = document.querySelector(".footer");
const body = document.body;
const modalTarjeta = document.getElementById("modalTarjeta");


let productosOriginales = []; 

async function obtenerProductos() {
    try {
        const respuesta = await fetch('http://localhost:8080/api/productos');
        productosOriginales = await respuesta.json(); 
        console.log("Datos guardados correctamente:", productosOriginales.length);
        cargarProductos(productosOriginales);
    } catch (error) {
        console.error("Error al cargar:", error);
    }
}

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

obtenerProductos();

function abrirDetallePlato(id) {
    const producto = productosOriginales.find(p => p.id === id);

    if (producto) {
        document.getElementById("nombrePlato").innerText = producto.nombre;
        
       
        document.getElementById("imgPlato").innerHTML = `<img src="${producto.imagenUrl}" alt="${producto.nombre}">`;
        
        const textoAlergenos = producto.alergeno.nombre || "Sin alérgenos";
        document.getElementById("nombreAlergeno").innerText = `Contiene: ${textoAlergenos}`;
        
        document.getElementById("descAlergeno").innerText = producto.alergeno.descripcion || "";

        document.getElementById("modalPlato").style.display = "flex";
    }
}


document.querySelector(".btn-cerrar-plato").addEventListener("click", () => {
    document.getElementById("modalPlato").style.display = "none";
});



btnBusqueda.addEventListener("click", () => {
    const textoBuscado = inputBusqueda.value.toLowerCase();
    const tarjetasCargadas = document.querySelectorAll(".tarjeta");

    tarjetasCargadas.forEach(tarjeta => {
        const tituloTarjeta = tarjeta.querySelector(".titulo").textContent.toLowerCase();

        // Si el título tiene el texto que se busca, se muestra, si no se oculta
        if (tituloTarjeta.includes(textoBuscado)) {
            tarjeta.style.display = "flex";
        } else {
            tarjeta.style.display = "none";
        }
    });

});

 
const selectorCategorias = document.getElementById("slcPlatos");


selectorCategorias.addEventListener("change", () => {
    
    const idSeleccionado = parseInt(selectorCategorias.value);

    if (idSeleccionado === 0) {
        
        cargarProductos(productosOriginales);
    } else {

        const filtrados = productosOriginales.filter(p => p.categoria.id === idSeleccionado);
        cargarProductos(filtrados);
    }
});
let carrito = [];

function mostrarNotificacion(mensaje) {
    const noti = document.getElementById("notificacion");
    
    
    noti.innerText = mensaje
    noti.className = "notificacion-visible";
    setTimeout(() => {
        noti.className = "notificacion-oculta";
    }, 3000);
}

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

function guardarCarrito() {
    localStorage.setItem("carrito", JSON.stringify(carrito));
    actualizarContador();
}
function actualizarContador() {
    const contador = document.getElementById("contador-carrito");
    if (contador) {
        
        const totalProductos = carrito.reduce((total, item) => total + item.cantidad, 0);
        contador.innerText = totalProductos;
    }
}


document.getElementById("btnCarrito").addEventListener("click", () => {
    modalTarjeta.style.display = "flex";
    mostrarCarrito();
});





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
            <div class="item-carrito" style="display:flex; justify-content:space-between; margin-bottom:10px;">
                <p><strong>${item.nombre}</strong> x ${item.cantidad}</p>
                <p>${subtotal.toFixed(2)}€</p>
            </div>
            <hr>`;
    });

    html += `<h4 >Total: ${totalPagar.toFixed(2)}€</h4>`;
    html += `<button class="btn-pagar">Finalizar Compra</button>`;
    html += `<button class="btn-borrar">Vaciar Carrito</button>`;
    html += `<button class="btn-cerrar">Cerrar</button>`;
    
    contenedorItems.innerHTML = html;
}


document.getElementById("btnCarrito").addEventListener("click", () => {
    
    const modalCarro = document.getElementById("modalTarjeta");
    modalCarro.style.display = "flex";
    mostrarCarrito();
});

const btnCerrar = document.querySelector(".btn-cerrar");
const btnBorrar = document.querySelector(".btn-borrar");




document.addEventListener("click", (event) => {
    if (event.target.classList.contains("btn-cerrar")) {
        cerrarModal();
    }
});

document.addEventListener("click", (event) => {
    if (event.target.classList.contains("btn-borrar")) {
        carrito = []
        actualizarContador();
        cerrarModal();
    }
});

function cerrarModal() {
    modalTarjeta.style.display = "none";
    
}