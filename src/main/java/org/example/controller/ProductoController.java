package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Producto;
import org.example.service.ProductoService;

import java.util.List;

public class ProductoController {
    private ProductoService service = new ProductoService();

    public String request() {
        List<Producto> lista = service.obtenerCatalogo();
        return new Gson().toJson(lista);
    }
}
