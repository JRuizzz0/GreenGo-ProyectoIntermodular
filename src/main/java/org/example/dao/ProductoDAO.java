package org.example.dao;


import org.example.config.DatabaseConfig;
import org.example.model.Categoria;
import org.example.model.Impuesto;
import org.example.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();


        String sql = "SELECT p.*, c.nombre as cat_nombre, i.valor as imp_valor " +
                "FROM PRODUCTO p " +
                "JOIN CATEGORIAS c ON p.id_categoria = c.id_categoria " +
                "JOIN IMPUESTOS i ON p.id_impuesto = i.id_impuesto";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioBase(rs.getDouble("precio_base"));
                p.setImagenUrl(rs.getString("imagen_url"));
                p.setStock(rs.getInt("stock"));


                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("cat_nombre"));
                p.setCategoria(cat);


                Impuesto imp = new Impuesto();
                imp.setId(rs.getInt("id_impuesto"));
                imp.setValor(rs.getDouble("imp_valor"));
                p.setImpuesto(imp);

                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return productos;
    }
}