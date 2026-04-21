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
INSERT INTO PRODUCTO (nombre, descripcion, precio_base, imagen_url, stock, id_categoria, id_impuesto) VALUES 
-- CATEGORÍA 1: PRIMEROS PLATOS
('Gazpacho Andaluz Tradicional', 'Elaborado con tomates de huerta ecológica, pepino, pimiento y AOVE.', 4.80, 'https://images.unsplash.com/photo-1594756202469-9ff9799b2e42?q=80&w=500', 25, 1, 1),
('Crema de Calabaza y Jengibre', 'Suave crema de temporada con un toque picante y semillas de calabaza.', 5.20, 'https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?q=80&w=500', 15, 1, 1),
('Ensalada César Vegana', 'Lechuga romana, croutones de pan integral, tofu marinado y salsa césar casera.', 7.50, 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=500', 12, 1, 1),

-- CATEGORÍA 2: SEGUNDOS PLATOS
('Risotto de Setas Silvestres', 'Arroz carnaroli con variedad de setas de bosque y lascas de queso vegano.', 13.90, 'https://images.unsplash.com/photo-1476124369491-e7addf5db371?q=80&w=500', 10, 2, 1),
('Tacos de Jackfruit al Pastor', 'Tres tacos de "carne" vegetal con piña, cilantro y cebolla morada.', 10.50, 'https://images.unsplash.com/photo-1565299585323-38d6b0865b47?q=80&w=500', 18, 2, 1),
('Seitán al Horno con Patatas', 'Seitán casero marinado en finas hierbas con guarnición de patatas baby.', 11.80, 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?q=80&w=500', 8, 2, 1),

-- CATEGORÍA 3: POSTRES
('Tarta de Queso Vegana', 'Base de frutos secos y relleno cremoso de anacardos con mermelada de arándanos.', 5.50, 'https://images.unsplash.com/photo-1533134242443-d4fd215305ad?q=80&w=500', 10, 3, 1),
('Mousse de Chocolate y Avellana', 'Chocolate 70% cacao con base de aquafaba y trocitos de avellana tostada.', 4.20, 'https://images.unsplash.com/photo-1528451634235-900366627581?q=80&w=500', 14, 3, 1),
('Brochetas de Fruta de Temporada', 'Selección de frutas frescas cortadas con un toque de sirope de ágave.', 3.90, 'https://images.unsplash.com/photo-1519996529931-28324d5a630e?q=80&w=500', 20, 3, 1),

-- CATEGORÍA 4: BEBIDAS
('Limonada Casera con Menta', 'Zumo de limón natural, menta fresca y un toque de estevia.', 2.90, 'https://images.unsplash.com/photo-1523472721958-978152f4d69b?q=80&w=500', 40, 4, 2),
('Té Frío de Hibisco', 'Infusión natural de hibisco con frutos rojos y mucho hielo.', 3.20, 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?q=80&w=500', 35, 4, 2),
('Cerveza Artesana Local (Bio)', 'Cerveza rubia de producción ecológica y proximidad.', 4.50, 'https://images.unsplash.com/photo-1535958636474-b021ee887b13?q=80&w=500', 50, 4, 2);


CREATE TABLE Alergenos(
id_alergeno serial PRIMARY KEY,
nombre_alergeno Varchar(100) NOT NULL,
desc_alergeno TEXT Not null
);

CREATE TABLE Alergenos_producto(
id_alergeno_producto serial PRIMARY KEY,
id_alergeno int,
id_producto int,
info_alergia text,
FOREIGN KEY (id_alergeno) REFERENCES Alergenos(id_alergeno),
FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);
