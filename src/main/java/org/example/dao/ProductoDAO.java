package org.example.dao;


import org.example.config.DatabaseConfig;
import org.example.model.Alergenos;
import org.example.model.Categoria;
import org.example.model.Impuesto;
import org.example.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();


        String sql = """
    SELECT p.*, c.nombre AS cat_nombre, i.valor AS imp_valor, 
           STRING_AGG(a.nombre_alergeno, ', ') AS nombres_alergenos, STRING_AGG(a.desc_alergeno, '\n') AS descripcion_alergeno
    FROM PRODUCTO p
    LEFT JOIN CATEGORIAS c ON p.id_categoria = c.id_categoria
    LEFT JOIN IMPUESTOS i ON p.id_impuesto = i.id_impuesto
    LEFT JOIN Alergenos_producto ap ON p.id_producto = ap.id_producto
    LEFT JOIN Alergenos a ON ap.id_alergeno = a.id_alergeno
    GROUP BY p.id_producto, c.nombre, i.valor
    ORDER BY p.id_categoria ASC
    """;


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

                // Categoría
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("cat_nombre"));
                p.setCategoria(cat);

                // Impuesto
                Impuesto imp = new Impuesto();
                imp.setId(rs.getInt("id_impuesto"));
                imp.setValor(rs.getDouble("imp_valor"));
                p.setImpuesto(imp);

                // Alérgenos
                Alergenos a = new Alergenos();

                a.setId(0);


                String listaNombres = rs.getString("nombres_alergenos");
                String descripcionAlergeno = rs.getString("descripcion_alergeno");


                if (listaNombres != null) {
                    a.setNombre(listaNombres);
                    a.setDescripcion(descripcionAlergeno);
                } else {
                    a.setNombre("Sin alérgenos");
                    a.setDescripcion("Producto libre de alérgenos comunes.");
                }

                p.setAlergeno(a);

                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return productos;
    }
}