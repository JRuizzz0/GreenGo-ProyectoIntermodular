package org.example.service;

import org.example.model.Producto;
import org.example.model.Impuesto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el servicio ProductoService.
 */
public class ProductoServiceTest {

    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoService = new ProductoService();
    }

    /**
     * Test 1: Verifica que el catálogo de productos no sea null.
     */
    @Test
    public void testObtenerCatalogoNoDevuelveNull() {
        var catalogo = productoService.obtenerCatalogo();

        assertNotNull(catalogo, "El catálogo no debería ser null");
    }

    /**
     * Test 2: Verifica que el cálculo del precio final con impuesto es correcto.
     */
    @Test
    public void testCalcularPrecioFinalConImpuesto() {
        Producto producto = new Producto();
        producto.setPrecioBase(100.0);

        Impuesto impuesto = new Impuesto();
        impuesto.setValor(0.21); // IVA 21%
        producto.setImpuesto(impuesto);

        double precioFinal = producto.getPrecioFinal();

        assertEquals(121.0, precioFinal, 0.01, "El precio con IVA del 21% debería ser 121.0");
    }

    /**
     * Test 3: Verifica que el precio final sea igual al precio base si no hay impuesto.
     */
    @Test
    public void testCalcularPrecioFinalSinImpuesto() {
        Producto producto = new Producto();
        producto.setPrecioBase(100.0);
        producto.setImpuesto(null);

        double precioFinal = producto.getPrecioFinal();

        assertEquals(100.0, precioFinal, 0.01, "Sin impuesto, el precio final debe ser igual al precio base");
    }
}