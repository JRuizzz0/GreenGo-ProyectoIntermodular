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

/**
 * Gestión de usuarios, autenticación y validaciones en la base de datos.
 */
public class UsuarioDAO {

    /**
     * Obtiene y lista los usuarios registrados.
     *
     * @return Lista de objetos Usuario.
     */
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuarios, usuario, correo, contrasena FROM usuarios ";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id_usuarios") + " - " + rs.getString("usuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Registra un nuevo usuario tras validar sus datos.
     *
     * @param body JSON con los datos del usuario.
     * @return true si se insertó correctamente, false si falló la validación o el registro.
     */
    public boolean insertarUsuario(String body) {
        Gson gson = new Gson();
        JsonObject jsonBody = gson.fromJson(JsonParser.parseString(body), JsonObject.class);
        String usuario = jsonBody.get("usuario").getAsString();
        String correo = jsonBody.get("correo").getAsString();
        String contrasena = jsonBody.get("contrasena").getAsString();

        String sql = "INSERT INTO usuarios (usuario, correo, contrasena) VALUES (?, ?, ?)";

        boolean emailExiste = findByEmail(correo);
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
        } else {
            return false;
        }
    }

    /**
     * Verifica si las credenciales de acceso son válidas.
     *
     * @param body JSON con usuario y contraseña.
     * @return true si la contraseña coincide, false en caso contrario.
     */
    public boolean comprobarUsuario(String body) {
        Gson gson = new Gson();
        try {
            JsonObject jsonBody = gson.fromJson(body, JsonObject.class);
            String usuario = jsonBody.get("usuario").getAsString();
            String contrasenaPlano = jsonBody.get("contrasena").getAsString();

            String hashBD = getHashPasswordPorUsuario(usuario);

            if (hashBD == null) {
                return false;
            }

            BCrypt.Result result = BCrypt.verifyer().verify(contrasenaPlano.getBytes(), hashBD.getBytes());
            return result.verified;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recupera la contraseña cifrada de un usuario específico.
     *
     * @param usuario Nombre del usuario.
     * @return Hash de la contraseña o null si no existe.
     */
    public String getHashPasswordPorUsuario(String usuario) {
        String sql = "SELECT contrasena FROM usuarios WHERE usuario = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("contrasena");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Comprueba si un correo electrónico ya existe en la base de datos.
     *
     * @param EmailBuscado Correo a verificar.
     * @return true si el correo ya está registrado, false si está libre.
     */
    public boolean findByEmail(String EmailBuscado) {
        boolean found = true;
        String sql = "SELECT id_usuarios, usuario FROM usuarios WHERE correo = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, EmailBuscado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Ya existe un usuario con ese correo.");
            } else {
                found = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    /**
     * Valida si el formato del correo es correcto mediante una expresión regular.
     *
     * @param emailBuscado Correo a validar.
     * @return true si es válido, false si no.
     */
    public boolean emailValido(String emailBuscado) {
        String regex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
        return emailBuscado != null && Pattern.matches(regex, emailBuscado);
    }

    /**
     * Valida que la contraseña cumpla con los requisitos mínimos de seguridad.
     *
     * @param contrasena Contraseña a validar.
     * @return true si cumple los requisitos, false si es débil.
     */
    public boolean contrasenaValida(String contrasena) {
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$";
        return contrasena.matches(regex);
    }
}