package org.example.service;

import org.example.dao.PedidoDAO;
import org.example.model.Pedido;

public class PedidoService {

    private PedidoDAO pedidoDAO = new PedidoDAO();

    public boolean procesarPedido(Pedido pedido) {

        if (pedido == null || pedido.getLineas() == null || pedido.getLineas().isEmpty()) {
            return false;
        }
        return pedidoDAO.guardarPedidoCompleto(pedido);
    }
}