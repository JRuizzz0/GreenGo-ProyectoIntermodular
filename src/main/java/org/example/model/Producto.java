package org.example.model;



public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private String imagenUrl;
    private int stock;
    private Categoria categoria;
    private Impuesto impuesto;
    private Alergenos alergeno;


    public Producto() {}

    public Producto(int id, String nombre, double precioBase, Impuesto impuesto) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.impuesto = impuesto;
    }

    public double getPrecioFinal() {
        if (this.impuesto != null) {
            return this.precioBase * (1 + this.impuesto.getValor());
        }
        return this.precioBase;
    }


    public Impuesto getImpuesto() { return impuesto; }
    public void setImpuesto(Impuesto impuesto) { this.impuesto = impuesto; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Alergenos getAlergenos() {
        return alergeno;
    }
    public void setAlergeno(Alergenos alergeno) {
        this.alergeno = alergeno;
    }
}