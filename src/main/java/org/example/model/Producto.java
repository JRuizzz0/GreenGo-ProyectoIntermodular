package org.example.model;

/**
 * Modelo que representa un producto del catálogo.
 */
public class Producto {
    /** ID único del producto. */
    private int id;
    /** Nombre del producto. */
    private String nombre;
    /** Descripción detallada. */
    private String descripcion;
    /** Precio sin impuestos. */
    private double precioBase;
    /** URL de la imagen del producto. */
    private String imagenUrl;
    /** Cantidad disponible. */
    private int stock;
    /** Categoría a la que pertenece. */
    private Categoria categoria;
    /** Impuesto aplicable. */
    private Impuesto impuesto;
    /** Información de alérgenos. */
    private Alergenos alergeno;

    /** Constructor por defecto. */
    public Producto() {}

    /**
     * Constructor con campos esenciales.
     *
     * @param id Identificador.
     * @param nombre Nombre.
     * @param precioBase Precio inicial.
     * @param impuesto Objeto de impuesto.
     */
    public Producto(int id, String nombre, double precioBase, Impuesto impuesto) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.impuesto = impuesto;
    }

    /**
     * Calcula el precio del producto aplicando el impuesto correspondiente.
     *
     * @return Precio total con impuestos o precio base si no hay impuesto definido.
     */
    public double getPrecioFinal() {
        if (this.impuesto != null) {
            return this.precioBase * (1 + this.impuesto.getValor());
        }
        return this.precioBase;
    }

    /** @return Impuesto del producto. */
    public Impuesto getImpuesto() { return impuesto; }
    /** @param impuesto Impuesto a asignar. */
    public void setImpuesto(Impuesto impuesto) { this.impuesto = impuesto; }

    /** @return ID del producto. */
    public int getId() { return id; }
    /** @param id ID a asignar. */
    public void setId(int id) { this.id = id; }

    /** @return Nombre del producto. */
    public String getNombre() { return nombre; }
    /** @param nombre Nombre a asignar. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return Descripción del producto. */
    public String getDescripcion() { return descripcion; }
    /** @param descripcion Descripción a asignar. */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /** @return Precio base. */
    public double getPrecioBase() { return precioBase; }
    /** @param precioBase Precio base a asignar. */
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    /** @return URL de la imagen. */
    public String getImagenUrl() { return imagenUrl; }
    /** @param imagenUrl URL a asignar. */
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    /** @return Stock disponible. */
    public int getStock() { return stock; }
    /** @param stock Cantidad a asignar. */
    public void setStock(int stock) { this.stock = stock; }

    /** @return Categoría del producto. */
    public Categoria getCategoria() { return categoria; }
    /** @param categoria Categoría a asignar. */
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    /** @return Alérgenos del producto. */
    public Alergenos getAlergenos() { return alergeno; }
    /** @param alergeno Alérgeno a asignar. */
    public void setAlergeno(Alergenos alergeno) { this.alergeno = alergeno; }
}