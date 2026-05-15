package org.example.service;

import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;

import java.util.List;

/**
 * Lógica de negocio para la gestión de usuarios.
 */
public class UsuarioService {

    /** DAO para el acceso a datos de usuarios. */
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Recupera la lista de todos los usuarios registrados.
     *
     * @return Lista de objetos {@link Usuario}.
     */
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> lista = usuarioDAO.listarUsuarios();
        return lista;
    }
}