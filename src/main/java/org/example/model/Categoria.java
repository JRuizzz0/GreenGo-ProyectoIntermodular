package org.example.model;

/**
 * Modelo que representa una categoría de productos.
 */
public class Categoria {

    /** ID único de la categoría. */
    private int id;

    /** Nombre de la categoría. */
    private String nombre;

    /** Descripción de la categoría. */
    private String descripcion;

    /** Constructor vacío. */
    public Categoria() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param id Identificador.
     * @param nombre Nombre.
     * @param descripcion Detalle.
     */
    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /** @return ID de la categoría. */
    public int getId() {
        return id;
    }

    /** @param id ID a asignar. */
    public void setId(int id) {
        this.id = id;
    }

    /** @return Nombre de la categoría. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre Nombre a asignar. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return Descripción de la categoría. */
    public String getDescripcion() {
        return descripcion;
    }

    /** @param descripcion Descripción a asignar. */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}