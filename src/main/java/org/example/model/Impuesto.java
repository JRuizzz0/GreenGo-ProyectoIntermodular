package org.example.model;

public class Impuesto {
    private int id;
    private String tipoNombre;
    private double valor;

    public Impuesto() {
    }

    public Impuesto(int id, String tipoNombre, double valor) {
        this.id = id;
        this.tipoNombre = tipoNombre;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(String tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}