package org.example.dao;




import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.config.DatabaseConfig;
import org.example.model.Usuario;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {


    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT id, usuario, correo, contrasena FROM usuarios ";

        try (Connection conn = DatabaseConfig.getConnection();
             // Prepara la sentencia SQL para evitar errores y ataques (SQL Injection).
             PreparedStatement stmt = conn.prepareStatement(sql);
             // Ejecuta la consulta y guarda los resultados en un ResultSet.
             ResultSet rs = stmt.executeQuery()) {
            // Itera por cada fila devuelta por la consulta.
            while (rs.next()) {
                // Obtiene los datos de cada columna ("id" y "nombre") y los imprime por consola.
                System.out.println(rs.getInt("id") + " - " + rs.getString("usuario"));
            }
        } catch (Exception e) {
            // Si ocurre cualquier error (conexión, SQL, lectura), se imprime la traza para depurar.
            e.printStackTrace();
        }
        return usuarios;
    }
    public void insertarUsuario(String body) {
        Gson gson = new Gson();
        JsonObject jsonBody = gson.fromJson(JsonParser.parseString(body), JsonObject.class);
        String usuario = jsonBody.get("usuario").getAsString();
        String correo = jsonBody.get("correo").getAsString();
        String contrasena = jsonBody.get("contrasena").getAsString();
        String sql = "INSERT INTO usuarios (usuario, correo, contrasena) VALUES (?, ?, ?)";
        boolean emailExiste= findByEmail(correo);
        if (!emailExiste) {
            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario);
                stmt.setString(2, correo);
                stmt.setString(3, contrasena);
                stmt.executeUpdate();

                System.out.println("Usuario insertado: " + usuario);
                listarUsuarios();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public boolean comprobarUsuario(String body) {
        Gson gson = new Gson();
        JsonObject jsonBody = gson.fromJson(JsonParser.parseString(body), JsonObject.class);
        String correo = jsonBody.get("correo").getAsString();
        String contrasena = jsonBody.get("contrasena").getAsString();
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Usuario encontrado: " + rs.getString("nombre"));
                    return true;
                } else {
                    System.out.println("Correo o contraseña incorrecta");
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findByEmail(String EmailBuscado) {
        boolean found = true;
        String sql = "SELECT id, usuario FROM usuarios WHERE correo = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Sustituye el ? por el nombre que queremos buscar.
            stmt.setString(1, EmailBuscado);

            // Ejecuta la consulta SELECT.
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Si existe al menos un usuario con ese nombre...
                System.out.println("Ya existe un usuario con ese correo en la Base de datos. Pon otro");
            } else {
                System.out.println("No existe ningún usuario con el correo: " + EmailBuscado);
                found = false;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }
}

