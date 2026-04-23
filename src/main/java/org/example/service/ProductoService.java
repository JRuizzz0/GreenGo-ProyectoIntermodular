package org.example.service;

import org.example.dao.ProductoDAO;
import org.example.model.Producto;

import java.util.List;

public class ProductoService {
    private ProductoDAO productoDAO = new ProductoDAO();
    public List<Producto> obtenerCatalogo() {
        List<Producto> lista = productoDAO.listarTodos();
        return lista;
    }
}