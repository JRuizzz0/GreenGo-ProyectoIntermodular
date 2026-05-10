package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.util.List;

/**
 * Controlador para la gestión de usuarios.
 */
public class UsuarioController {

    /** Servicio para el manejo de lógica de usuarios. */
    private UsuarioService service = new UsuarioService();

    /**
     * Obtiene todos los usuarios registrados en formato JSON.
     *
     * @return String con la lista de usuarios serializada.
     */
    public String request() {
        List<Usuario> lista = service.obtenerUsuarios();
        return new Gson().toJson(lista);
    }
}