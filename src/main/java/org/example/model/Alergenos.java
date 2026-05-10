package org.example.model;

/**
 * Modelo que representa la información de un alérgeno.
 */
public class Alergenos {

    /** ID único del alérgeno. */
    private int id;

    /** Nombre del alérgeno. */
    private String nombre;

    /** Descripción detallada del alérgeno. */
    private String descripcion;

    /** Constructor vacío. */
    public Alergenos() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param id Identificador.
     * @param nombre Nombre.
     * @param descripcion Detalle.
     */
    public Alergenos(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /** @return ID del alérgeno. */
    public int getId() {
        return id;
    }

    /** @param id ID a asignar. */
    public void setId(int id) {
        this.id = id;
    }

    /** @return Nombre del alérgeno. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre Nombre a asignar. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return Descripción del alérgeno. */
    public String getDescripcion() {
        return descripcion;
    }

    /** @param descripcion Descripción a asignar. */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}