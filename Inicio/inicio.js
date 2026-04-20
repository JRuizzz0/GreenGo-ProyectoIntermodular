
const contTarjetas = document.getElementById("tarjetas");
const inputBusqueda = document.getElementById("inpTexto");
const btnBusqueda = document.getElementById("btnBusqueda");
const header = document.querySelector(".header");
const main = document.querySelector("main");
const footer = document.querySelector(".footer");
const body = document.body;
const modalTarjeta = document.getElementById("modalTarjeta");


function pintarProductos(lista) {
    const contenedor = document.getElementById('tarjetas');
    contenedor.innerHTML = ""; 

    lista.forEach(p => {
        contenedor.innerHTML += `
            <div class="tarjeta">
                <img src="${p.imagen_url}" alt="${p.nombre}" class="imagen-plato">
                <div class="titulo">${p.nombre}</div>
                <p class="descripcion">${p.descripcion}</p>
                <div class="precio-seccion">
                    ${(p.precio_base * 1.1).toFixed(2)}€ <small>(IVA inc.)</small>
                </div>
                <button onclick="comprar(${p.id_producto})">Añadir al carrito</button>
            </div>
        `;
    });
}

pintarProductos();

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


