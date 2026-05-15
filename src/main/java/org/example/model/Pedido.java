package org.example.model;
import java.util.List;

/**
 * Modelo que representa la cabecera y el detalle de un pedido.
 */
public class Pedido {

    /** Nombre del cliente que realiza el pedido. */
    private String nombreCliente;

    /** Dirección de entrega del pedido. */
    private String direccion;

    /** Importe total del pedido. */
    private double total;

    /** Listado de líneas que componen el pedido. */
    private List<LineaPedido> lineas;

    /** Constructor por defecto. */
    public Pedido() {}

    /** @return Nombre del cliente. */
    public String getNombreCliente() { return nombreCliente; }

    /** @param nombreCliente Nombre a asignar. */
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    /** @return Dirección de entrega. */
    public String getDireccion() { return direccion; }

    /** @param direccion Dirección a asignar. */
    public void setDireccion(String direccion) { this.direccion = direccion; }

    /** @return Total del pedido. */
    public double getTotal() { return total; }

    /** @param total Total a asignar. */
    public void setTotal(double total) { this.total = total; }

    /** @return Lista de líneas del pedido. */
    public List<LineaPedido> getLineas() { return lineas; }

    /** @param lineas Lista de líneas a asignar. */
    public void setLineas(List<LineaPedido> lineas) { this.lineas = lineas; }
}