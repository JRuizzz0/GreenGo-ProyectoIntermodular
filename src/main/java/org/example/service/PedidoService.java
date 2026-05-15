package org.example.service;

import org.example.dao.PedidoDAO;
import org.example.model.Pedido;

/**
 * Lógica de negocio para la gestión de pedidos.
 */
public class PedidoService {

    /** Objeto DAO para operaciones en base de datos. */
    private PedidoDAO pedidoDAO = new PedidoDAO();

    /**
     * Valida y procesa un pedido antes de guardarlo.
     *
     * @param pedido Objeto Pedido a procesar.
     * @return true si es válido y se guarda correctamente, false si está vacío o falla al guardar.
     */
    public boolean procesarPedido(Pedido pedido) {
        if (pedido == null || pedido.getLineas() == null || pedido.getLineas().isEmpty()) {
            return false;
        }
        return pedidoDAO.guardarPedidoCompleto(pedido);
    }
}