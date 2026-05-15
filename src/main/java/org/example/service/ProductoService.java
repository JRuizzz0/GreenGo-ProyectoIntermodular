package org.example.service;

import org.example.dao.ProductoDAO;
import org.example.model.Producto;

import java.util.List;

/**
 * Lógica de negocio para la gestión de productos.
 */
public class ProductoService {

    /** DAO para el acceso a datos de productos. */
    private ProductoDAO productoDAO = new ProductoDAO();

    /**
     * Recupera el catálogo completo de productos disponibles.
     *
     * @return Lista de objetos {@link Producto}.
     */
    public List<Producto> obtenerCatalogo() {
        List<Producto> lista = productoDAO.listarTodos();
        return lista;
    }
}