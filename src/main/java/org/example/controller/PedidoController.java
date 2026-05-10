package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Pedido;
import org.example.service.PedidoService;

/**
 * Controlador para gestionar las operaciones de pedidos.
 */
public class PedidoController {

    /** Servicio de lógica de negocio para pedidos. */
    private PedidoService service = new PedidoService();

    /** Utilidad para conversión de formato JSON. */
    private Gson gson = new Gson();

    /**
     * Procesa un JSON de pedido y lo guarda en el sistema.
     *
     * @param jsonBody Cuerpo de la petición en formato JSON.
     * @return JSON con el mensaje de éxito o error.
     */
    public String guardarPedido(String jsonBody) {
        try {
            // Convierte el JSON a objeto Pedido
            Pedido nuevoPedido = gson.fromJson(jsonBody, Pedido.class);

            // Intenta procesar el pedido mediante el servicio
            boolean exito = service.procesarPedido(nuevoPedido);

            if (exito) {
                return "{\"mensaje\": \"Pedido guardado correctamente\"}";
            } else {
                return "{\"error\": \"No se pudo guardar el pedido\"}";
            }

        } catch (Exception e) {
            System.err.println("Error al procesar el JSON: " + e.getMessage());
            return "{\"error\": \"Formato de datos incorrecto\"}";
        }
    }
}