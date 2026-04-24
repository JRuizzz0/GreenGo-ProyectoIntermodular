package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Pedido;
import org.example.service.PedidoService;

public class PedidoController {

    private PedidoService service = new PedidoService();
    private Gson gson = new Gson();
    
    
    public String guardarPedido(String jsonBody) {
        try {
            
            Pedido nuevoPedido = gson.fromJson(jsonBody, Pedido.class);

            
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