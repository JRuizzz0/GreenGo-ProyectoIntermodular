package org.example.model;
import java.util.List;

public class Pedido {
    private String nombreCliente;
    private String direccion;
    private double total;
    private List<LineaPedido> lineas;


    public Pedido() {}

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<LineaPedido> getLineas() { return lineas; }
    public void setLineas(List<LineaPedido> lineas) { this.lineas = lineas; }
}