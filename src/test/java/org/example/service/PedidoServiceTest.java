package org.example.service;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el servicio PedidoService.
 */
public class PedidoServiceTest {

    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoService();
    }

    /**
     * Test 1: Verifica que un pedido nulo sea rechazado.
     */
    @Test
    public void testProcesarPedidoNuloDevuelveFalse() {
        boolean resultado = pedidoService.procesarPedido(null);

        assertFalse(resultado, "Un pedido nulo debería ser rechazado");
    }

    /**
     * Test 2: Verifica que un pedido sin líneas sea rechazado.
     */
    @Test
    public void testProcesarPedidoSinLineasDevuelveFalse() {
        Pedido pedido = new Pedido();
        pedido.setNombreCliente("Juan Pérez");
        pedido.setDireccion("Calle Falsa 123");
        pedido.setTotal(100.0);
        pedido.setLineas(null); // Sin líneas

        boolean resultado = pedidoService.procesarPedido(pedido);

        assertFalse(resultado, "Un pedido sin líneas debería ser rechazado");
    }

    /**
     * Test 3: Verifica que un pedido con líneas vacías sea rechazado.
     */
    @Test
    public void testProcesarPedidoConLineasVaciasDevuelveFalse() {
        Pedido pedido = new Pedido();
        pedido.setNombreCliente("María García");
        pedido.setDireccion("Avenida Central 456");
        pedido.setTotal(200.0);
        pedido.setLineas(new ArrayList<>()); // Lista vacía

        boolean resultado = pedidoService.procesarPedido(pedido);

        assertFalse(resultado, "Un pedido con lista de líneas vacía debería ser rechazado");
    }
}