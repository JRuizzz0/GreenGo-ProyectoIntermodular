package org.example.dao;




import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.config.DatabaseConfig;
import org.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


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
    public boolean insertarUsuario(String body) {
        Gson gson = new Gson();
        JsonObject jsonBody = gson.fromJson(JsonParser.parseString(body), JsonObject.class);
        String usuario = jsonBody.get("usuario").getAsString();
        String correo = jsonBody.get("correo").getAsString();
        String contrasena = jsonBody.get("contrasena").getAsString();
        String sql = "INSERT INTO usuarios (usuario, correo, contrasena) VALUES (?, ?, ?)";
        boolean emailExiste= findByEmail(correo);
        boolean emailBien = emailValido(correo);
        boolean contrasenaBien = contrasenaValida(contrasena);
        if (!emailExiste && emailBien && contrasenaBien) {
            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                String bcryptHashString = BCrypt.withDefaults().hashToString(12, contrasena.toCharArray());

                stmt.setString(1, usuario);
                stmt.setString(2, correo);
                stmt.setString(3, bcryptHashString);
                stmt.executeUpdate();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        else {
            return false;
        }

    }
    public boolean existeUsuario(String usuario, String contrasena) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean comprobarUsuario(String body) {
        Gson gson = new Gson();

        try {
            JsonObject jsonBody = gson.fromJson(body, JsonObject.class);
            String usuario = jsonBody.get("usuario").getAsString();
            String contrasena = jsonBody.get("contrasena").getAsString();

            boolean existe = existeUsuario(usuario, contrasena);

            if (existe) {
                return true;
            } else {
                return false;
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


    public boolean emailValido(String emailBuscado) {
        String regex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
        if (emailBuscado == null || !Pattern.matches(regex, emailBuscado)) {
            return false; // Formato incorrecto, salimos directamente
        }
        else {
            return true;
        }
    }

    public boolean contrasenaValida(String contrasena) {
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$";
        if (contrasena.matches(regex)) {
            System.out.println("Contraseña válida");
            return true;
        } else {
            System.out.println("Contraseña inválida");
            return false;
        }
    }
}

