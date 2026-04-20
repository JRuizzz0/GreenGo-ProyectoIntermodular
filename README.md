# GreenGo-ProyectoIntermodular


Scripts Base de Datos: 
CREATE TABLE CATEGORIAS (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255)
);


INSERT INTO CATEGORIAS (nombre) VALUES ('Primeros platos'), ('Segundos platos'), ('Postres'), ('Bebidas');

CREATE TABLE IMPUESTOS (
    id_impuesto SERIAL PRIMARY KEY,
    tipo_nombre VARCHAR(50) NOT NULL,
    valor DECIMAL(5, 2) NOT NULL
);


INSERT INTO IMPUESTOS (tipo_nombre, valor) VALUES ('Comida (10%)', 0.10), ('Bebida (21%)', 0.21);

CREATE TABLE producto (
    id_producto serial PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio_base DECIMAL(10, 2) NOT NULL,
    imagen_url VARCHAR(255),
    stock INT DEFAULT 0,
    id_categoria INT,
    id_impuesto INT,
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIAS(id_categoria),
    FOREIGN KEY (id_impuesto) REFERENCES IMPUESTOS(id_impuesto)
);
