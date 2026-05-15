package org.example.model;

/**
 * Modelo que representa a un usuario del sistema.
 */
public class Usuario {
    /** ID único del usuario. */
    private int id;
    /** Nombre de cuenta del usuario. */
    private String usuario;
    /** Correo electrónico de contacto. */
    private String correo;
    /** Contraseña cifrada del usuario. */
    private String contrasena;

    /** Constructor por defecto. */
    public Usuario() {}

    /**
     * Constructor con todos los campos.
     *
     * @param id Identificador.
     * @param usuario Nombre de usuario.
     * @param correo Correo electrónico.
     * @param contrasena Contraseña.
     */
    public Usuario(int id, String usuario, String correo, String contrasena) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    /** @return ID del usuario. */
    public int getId() { return id; }
    /** @param id ID a asignar. */
    public void setId(int id) { this.id = id; }

    /** @return Nombre de usuario. */
    public String getUsuario() { return usuario; }
    /** @param usuario Nombre a asignar. */
    public void setUsuario(String usuario) { this.usuario = usuario; }

    /** @return Correo del usuario. */
    public String getCorreo() { return correo; }
    /** @param correo Correo a asignar. */
    public void setCorreo(String correo) { this.correo = correo; }

    /** @return Contraseña del usuario. */
    public String getContrasena() { return contrasena; }
    /** @param contrasena Contraseña a asignar. */
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}