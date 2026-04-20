
const contTarjetas = document.getElementById("tarjetas");
const inputBusqueda = document.getElementById("inpTexto");
const btnBusqueda = document.getElementById("btnBusqueda");
const header = document.querySelector(".header");
const main = document.querySelector("main");
const footer = document.querySelector(".footer");
const body = document.body;
const modalTarjeta = document.getElementById("modalTarjeta");



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


