package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.LineaPedido;
import org.example.model.Pedido;

import java.sql.*;

public class PedidoDAO {

    public boolean guardarPedidoCompleto(Pedido pedido) {
        String sqlPedido = "INSERT INTO Pedido (nombre_cliente, direccion, total) VALUES (?, ?, ?) RETURNING id_pedido";
        String sqlDetalle = "INSERT INTO Detalles_Pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection()) {


            try (PreparedStatement ps = conn.prepareStatement(sqlPedido)) {
                ps.setString(1, pedido.getNombreCliente());
                ps.setString(2, pedido.getDireccion());
                ps.setDouble(3, pedido.getTotal());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int idGenerado = rs.getInt("id_pedido"); // Obtenemos el ID del nuevo ticket


                    try (PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle)) {
                        for (LineaPedido linea : pedido.getLineas()) {
                            psDetalle.setInt(1, idGenerado);
                            psDetalle.setInt(2, linea.getIdProducto());
                            psDetalle.setInt(3, linea.getCantidad());
                            psDetalle.setDouble(4, linea.getPrecioUnitario());
                            psDetalle.executeUpdate();
                        }
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar pedido: " + e.getMessage());
        }
        return false;
    }
}