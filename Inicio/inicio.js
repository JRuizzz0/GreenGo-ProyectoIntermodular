
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
            <div class="tarjeta">
                <img src="${p.imagenUrl}" alt="${p.nombre}" class="imagen-plato">
                <div class="titulo">${p.nombre}</div>
                <p class="descripcion">${p.descripcion}</p>
                <div class="precio-seccion">
                    ${(p.precioBase * 1.1).toFixed(2)}€ <small>(IVA inc.)</small>
                </div>
                <button onclick="comprar(${p.id})" id="btnComprar">Añadir al carrito</button>
            </div>
        `;
    });
}


obtenerProductos();

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
    const toast = document.getElementById("notificacion");
    
    
    toast.innerText = mensaje
    toast.className = "notificacion-visible";
    setTimeout(() => {
        toast.className = "notificacion-oculta";
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
    const contenedorModal = document.querySelector(".modal_content"); 
    
    if (carrito.length === 0) {
        contenedorModal.innerHTML = "<h3>Tu carrito está vacío</h3>";
        return;
    }

    let html = "<h3>Tu Pedido</h3>";
    let totalPagar = 0;

    carrito.forEach(item => {
        const subtotal = (item.precioBase * 1.1) * item.cantidad;
        totalPagar += subtotal;
        html += `
            <div class="item-carrito">
                <p>${item.nombre} x ${item.cantidad}</p>
                <p>${subtotal.toFixed(2)}€</p>
                <hr>
            </div>
        `;
    });

    html += `<h4>Total: ${totalPagar.toFixed(2)}€</h4>`;
    html += `<button class="btn-pagar">Finalizar Compra</button>`;
    html += `<button class="btn-cerrar">Cerrar Carrito</button>`;
    
    contenedorModal.innerHTML = html;
}

const btnCerrar = document.querySelector(".btn-cerrar");




document.addEventListener("click", (event) => {
    if (event.target.classList.contains("btn-cerrar")) {
        cerrarModal();
    }
});

function cerrarModal() {
    modalTarjeta.style.display = "none";
    carrito = [];
    actualizarContador();
}