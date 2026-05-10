package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Producto;
import org.example.service.ProductoService;

import java.util.List;

/**
 * Controlador para la gestión de productos.
 */
public class ProductoController {

    /** Servicio para manejar la lógica de productos. */
    private ProductoService service = new ProductoService();

    /**
     * Obtiene el catálogo completo de productos en formato JSON.
     *
     * @return String con la lista de productos serializada.
     */
    public String request() {
        List<Producto> lista = service.obtenerCatalogo();
        return new Gson().toJson(lista);
    }
}