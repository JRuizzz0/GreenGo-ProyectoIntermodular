package org.example.service;


import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;


import java.util.List;


public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> lista = usuarioDAO.listarUsuarios();
        return lista;
    }
}
