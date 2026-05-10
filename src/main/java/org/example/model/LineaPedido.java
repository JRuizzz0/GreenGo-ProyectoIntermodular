package org.example.model;

/**
 * Representa una línea individual dentro de un pedido.
 */
public class LineaPedido {

    /** Identificador del producto. */
    private int idProducto;

    /** Cantidad solicitada. */
    private int cantidad;

    /** Precio por unidad en el momento del pedido. */
    private double precioUnitario;

    /** Constructor por defecto. */
    public LineaPedido() {}

    /** @return ID del producto. */
    public int getIdProducto() { return idProducto; }

    /** @param idProducto ID del producto a asignar. */
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    /** @return Cantidad del producto. */
    public int getCantidad() { return cantidad; }

    /** @param cantidad Cantidad a asignar. */
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    /** @return Precio unitario. */
    public double getPrecioUnitario() { return precioUnitario; }

    /** @param precioUnitario Precio a asignar. */
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
}