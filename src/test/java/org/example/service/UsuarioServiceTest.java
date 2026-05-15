package org.example.service;

import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el servicio UsuarioService (versión adaptada).
 */
public class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioDAO usuarioDAO;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Test 1: Email inválido debe ser rechazado.
     */
    @Test
    public void testEmailInvalidoLanzaExcepcion() {
        String emailInvalido = "correo-mal-formato";

        boolean esValido = usuarioDAO.emailValido(emailInvalido);

        assertFalse(esValido, "El email '" + emailInvalido + "' debería ser inválido");
    }

    /**
     * Test 2: Contraseña débil debe ser rechazada.
     */
    @Test
    public void testContrasenaDebilLanzaExcepcion() {
        String contrasenaDebil = "123";

        boolean esValida = usuarioDAO.contrasenaValida(contrasenaDebil);

        assertFalse(esValida, "La contraseña '" + contrasenaDebil + "' es demasiado débil");
    }

    /**
     * Test 3: Contraseña con estructura incorrecta (sin carácter especial).
     */
    @Test
    public void testContrasenaSinCaracterEspecialLanzaExcepcion() {
        String contrasenaSinEspecial = "Password123";

        boolean esValida = usuarioDAO.contrasenaValida(contrasenaSinEspecial);

        assertFalse(esValida, "La contraseña debe contener al menos un carácter especial (!@#$%^&*)");
    }
}