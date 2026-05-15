package org.example.model;

/**
 * Modelo que representa un impuesto aplicable a los productos.
 */
public class Impuesto {

    /** ID único del impuesto. */
    private int id;

    /** Nombre o tipo del impuesto (ej. IVA). */
    private String tipoNombre;

    /** Valor numérico o porcentaje del impuesto. */
    private double valor;

    /** Constructor vacío. */
    public Impuesto() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param id Identificador.
     * @param tipoNombre Nombre del tipo de impuesto.
     * @param valor Valor del impuesto.
     */
    public Impuesto(int id, String tipoNombre, double valor) {
        this.id = id;
        this.tipoNombre = tipoNombre;
        this.valor = valor;
    }

    /** @return ID del impuesto. */
    public int getId() {
        return id;
    }

    /** @param id ID a asignar. */
    public void setId(int id) {
        this.id = id;
    }

    /** @return Nombre del tipo de impuesto. */
    public String getTipoNombre() {
        return tipoNombre;
    }

    /** @param tipoNombre Nombre a asignar. */
    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    /** @return Valor del impuesto. */
    public double getValor() {
        return valor;
    }

    /** @param valor Valor a asignar. */
    public void setValor(double valor) {
        this.valor = valor;
    }
}