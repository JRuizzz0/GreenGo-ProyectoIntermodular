package org.example.controller;


import com.google.gson.Gson;
import org.example.model.Usuario;
import org.example.service.UsuarioService;


import java.util.List;


public class UsuarioController {
    private UsuarioService service = new UsuarioService();


    public String request() {
        List<Usuario> lista = service.obtenerUsuarios();
        return new Gson().toJson(lista);
    }
}
