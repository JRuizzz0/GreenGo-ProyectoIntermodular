# GreenGo-ProyectoIntermodular

#code:
 if (method.equalsIgnoreCase("POST")) {
                    addCorsHeaders(exchange);
                    try {
                        byte[] bytes = exchange.getRequestBody().readAllBytes();
                        String body = new String(bytes, StandardCharsets.UTF_8);
                        System.out.println("Cuerpo recibido: " + body);

                        JsonObject raiz = JsonParser.parseString(body).getAsJsonObject();
                        System.out.println(raiz.toString());

                        if (!raiz.has("nombre") || !raiz.has("email") || !raiz.has("contraseña")) {
                            sendResponse(exchange, 400, "{\"error\":\"Faltan campos requeridos\"}");
                            return;
                        }

                        String nombre = raiz.get("nombre").getAsString();
                        String email = raiz.get("email").getAsString();
                        String contraseña = raiz.get("contraseña").getAsString();
                        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!%@*?&])[A-Za-z\\d!%@*?&]{5,10}$";
                        if ( contraseña.matches(regex)){
                            System.out.println("Validación correcta");
                            String bcryptHashString = BCrypt.withDefaults().hashToString(12, contraseña.toCharArray());
                            System.out.println("Datos: " + nombre + ", " + email + ", " + bcryptHashString);
                            usuario.insertarUsuario(nombre, email, bcryptHashString);
                        }else {
                            System.out.println("La contraseña debe incluir al menos una mayuscula, un caracter especial, un digito y estar entre 5 y 10 chars");
                        }






                        sendResponse(exchange, 200, gson.toJson(raiz));

                    } catch (Exception e) {
                        e.printStackTrace();
                        sendResponse(exchange, 500, "{\"error\":\"Error al procesar la solicitud\"}");
                    }
                    return;
                }

                sendResponse(exchange, 405, "{\"error\":\"Método no permitido\"}");
            }
https://trello.com/b/D7SDZZRH/mi-tablero-de-trello
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

-- 1. INSERTAR ALÉRGENOS COMUNES
INSERT INTO Alergenos (nombre_alergeno, desc_alergeno) VALUES 
('Gluten', 'Presente en cereales como trigo, cebada o centeno.'),
('Frutos de cáscara', 'Incluye almendras, avellanas, nueces, anacardos, etc.'),
('Soja', 'Muy común en productos vegetales como el tofu o tempeh.'),
('Sésamo', 'Semillas de sésamo y productos derivados.'),
('Mostaza', 'Presente en salsas y condimentos.'),
('Apio', 'Incluye tallos, hojas, semillas y raíces.'),
('Sulfitos', 'Conservantes presentes habitualmente en vinos y vinagres.');

-- 2. RELACIONAR PRODUCTOS CON ALÉRGENOS
-- Usamos subconsultas para que busque el ID por el nombre del plato

-- Gazpacho (Sulfitos por el vinagre)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sulfitos'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Gazpacho Andaluz Tradicional'), 
        'Contiene vinagre de jerez con sulfitos.');

-- Seitán al Horno (Gluten y Soja - El seitán es puro gluten)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Seitán al Horno con Patatas'), 
        'El seitán es proteína de trigo.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Soja'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Seitán al Horno con Patatas'), 
        'Marinado con salsa de soja.');

-- Burger GreenGo (Gluten, Soja y Mostaza)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Pan de brioche artesanal.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Soja'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Proteína vegetal.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Mostaza'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Salsa secreta GreenGo.');

-- Hummus de Remolacha (Sésamo)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sésamo'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Hummus de Remolacha y Edamame'), 
        'Contiene pasta de tahini.');

-- Tarta de Queso Vegana (Frutos de cáscara)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Frutos de cáscara'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Tarta de Queso Vegana'), 
        'Base elaborada con anacardos y dátiles.');

-- Cerveza (Gluten)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Cerveza Artesana Local (Bio)'), 
        'Malta de cebada.');

-- Vino Tinto (Sulfitos)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sulfitos'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Vino Tinto Bio (Copa)'), 
        'Sulfitos naturales de la fermentación.');


        CREATE TABLE Pedido (
    id_pedido SERIAL PRIMARY KEY,
    nombre_cliente VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Líneas del ticket (Qué productos van dentro del pedido)
CREATE TABLE Detalles_Pedido (
    id_detalle SERIAL PRIMARY KEY,
    id_pedido INT REFERENCES Pedido(id_pedido) ON DELETE CASCADE,
    id_producto INT REFERENCES PRODUCTO(id_producto),
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL
);

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

-- RESET DE TODAS LAS IMÁGENES A LOS LINKS ORIGINALES

-- CATEGORÍA 1: PRIMEROS PLATOS
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1594756202469-9ff9799b2e42?q=80&w=500' WHERE nombre = 'Gazpacho Andaluz Tradicional';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?q=80&w=500' WHERE nombre = 'Crema de Calabaza y Jengibre';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=500' WHERE nombre = 'Ensalada César Vegana';

-- CATEGORÍA 2: SEGUNDOS PLATOS
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1476124369491-e7addf5db371?q=80&w=500' WHERE nombre = 'Risotto de Setas Silvestres';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1565299585323-38d6b0865b47?q=80&w=500' WHERE nombre = 'Tacos de Jackfruit al Pastor';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?q=80&w=500' WHERE nombre = 'Seitán al Horno con Patatas';

-- CATEGORÍA 3: POSTRES
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1533134242443-d4fd215305ad?q=80&w=500' WHERE nombre = 'Tarta de Queso Vegana';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1528451634235-900366627581?q=80&w=500' WHERE nombre = 'Mousse de Chocolate y Avellana';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1519996529931-28324d5a630e?q=80&w=500' WHERE nombre = 'Brochetas de Fruta de Temporada';

-- CATEGORÍA 4: BEBIDAS
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1523472721958-978152f4d69b?q=80&w=500' WHERE nombre = 'Limonada Casera con Menta';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?q=80&w=500' WHERE nombre = 'Té Frío de Hibisco';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1535958636474-b021ee887b13?q=80&w=500' WHERE nombre = 'Cerveza Artesana Local (Bio)';

-- 1. Gazpacho (Se ve el cuenco con el tomate y el verde, muy "eco")
UPDATE PRODUCTO 
SET imagen_url = 'https://images.unsplash.com/photo-1547592166-23ac45744acd?q=80&w=500' 
WHERE nombre = 'Gazpacho Andaluz Tradicional';

-- 2. Mousse de Chocolate (Se ve la textura cremosa en el vaso)
UPDATE PRODUCTO 
SET imagen_url = 'https://images.unsplash.com/photo-1541783245831-57d6fb0926d3?q=80&w=500' 
WHERE nombre = 'Mousse de Chocolate y Avellana';


update PRODUCTO
set nombre = 'Bol de Frutas de Temporada'
where nombre = 'Bol de Fruta de Temporada'

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


SELECT p.*, a.*, c.nombre as cat_nombre, i.valor as imp_valor
                FROM PRODUCTO p
                JOIN CATEGORIAS c ON p.id_categoria = c.id_categoria
                JOIN IMPUESTOS i ON p.id_impuesto = i.id_impuesto 
                LEFT JOIN alergenos_producto ap ON p.id_producto = ap.id_producto
				LEFT JOIN alergenos a on ap.id_alergeno = a.id_alergeno 
                ORDER BY p.id_categoria ASC, p.id_producto ASC;


-- AMPLIACIÓN DEL MENÚ GREENGÓ

INSERT INTO PRODUCTO (nombre, descripcion, precio_base, imagen_url, stock, id_categoria, id_impuesto) VALUES 

-- CATEGORÍA 1: PRIMEROS PLATOS
('Hummus de Remolacha y Edamame', 'Trío de hummus caseros servidos con bastoncitos de zanahoria y pan de pita integral.', 6.90, 'https://images.unsplash.com/photo-1577906030551-5b9164f440d8?q=80&w=500', 15, 1, 1),
('Gyozas de Shiitake y Col', '6 unidades de empanadillas japonesas al vapor con salsa de soja cítrica.', 8.20, 'https://images.unsplash.com/photo-1496116218417-1a781b1c416c?q=80&w=500', 12, 1, 1),

-- CATEGORÍA 2: SEGUNDOS PLATOS
('Burger GreenGo Especial', 'Hamburguesa de proteína de guisante, queso vegano, cebolla caramelizada y rúcula.', 14.50, 'https://images.unsplash.com/photo-1525059696034-4767759ad7ba?q=80&w=500', 20, 2, 1),
('Curry Verde de Tofu y Coco', 'Receta tailandesa original con leche de coco, verduras crujientes y arroz basmati.', 13.20, 'https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?q=80&w=500', 10, 2, 1),

-- CATEGORÍA 3: POSTRES
('Brownie de Batata y Nueces', 'Sorprendente brownie sin gluten ni azúcar refinado, servido con helado de coco.', 5.80, 'https://images.unsplash.com/photo-1606312619070-d48b4c652a52?q=80&w=500', 12, 3, 1),
('Pudding de Chía y Mango', 'Capas de semillas de chía hidratadas en leche de almendras con puré de mango fresco.', 4.50, 'https://images.unsplash.com/photo-1590080873978-9312759d6100?q=80&w=500', 18, 3, 1),

-- CATEGORÍA 4: BEBIDAS
('Kombucha de Jengibre y Limón', 'Bebida fermentada artesanal, probiótica y refrescante.', 3.80, 'https://images.unsplash.com/photo-1596464531735-2767c0469b66?q=80&w=500', 30, 4, 2),
('Vino Tinto Bio (Copa)', 'Vino de uva ecológica de viñedos locales sin sulfitos añadidos.', 4.00, 'https://images.unsplash.com/photo-1510812431401-41d2bd2722f3?q=80&w=500', 25, 4, 2);
-- ACTUALIZACIÓN DE IMÁGENES ESPECÍFICAS

-- REPARACIÓN DEFINITIVA DE IMÁGENES (GreenGo)

-- 1. Hummus de Remolacha (Color rosa vibrante, se ve el producto real)
UPDATE PRODUCTO 
SET imagen_url = 'https://th.bing.com/th/id/OIP.RKqDUPurSRPNzaHQFpxu0QHaEK?w=299&h=180&c=7&r=0&o=7&pid=1.7&rm=3' 
WHERE nombre = 'Hummus de Remolacha y Edamame';

-- 2. Burger GreenGo (Una burger vegetal de alta calidad con espinacas/rúcula)
UPDATE PRODUCTO 
SET imagen_url = 'https://i0.wp.com/fitonapp.com/wp-content/uploads/shutterstock_1239647506-scaled.jpg?resize=2048%2C1365&ssl=1' 
WHERE nombre = 'Burger GreenGo Especial';

-- 3. Pudding de Chía y Mango (Se ven perfectamente las semillas y el mango)
UPDATE PRODUCTO 
SET imagen_url = 'https://th.bing.com/th/id/OIP.l1kOiB5Rfh3yiF1hqqhYKQHaFj?w=243&h=182&c=7&r=0&o=7&pid=1.7&rm=3' 
WHERE nombre = 'Pudding de Chía y Mango';

-- 4. Kombucha (Botella artesanal con vaso y burbujas)
UPDATE PRODUCTO 
SET imagen_url = 'https://th.bing.com/th/id/OIP.FtGj-Xp1jCOVAE3MxdNT-AHaEO?w=306&h=180&c=7&r=0&o=7&pid=1.7&rm=3' 
WHERE nombre = 'Kombucha de Jengibre y Limón';


-- 1. INSERTAR ALÉRGENOS COMUNES
INSERT INTO Alergenos (nombre_alergeno, desc_alergeno) VALUES 
('Gluten', 'Presente en cereales como trigo, cebada o centeno.'),
('Frutos de cáscara', 'Incluye almendras, avellanas, nueces, anacardos, etc.'),
('Soja', 'Muy común en productos vegetales como el tofu o tempeh.'),
('Sésamo', 'Semillas de sésamo y productos derivados.'),
('Mostaza', 'Presente en salsas y condimentos.'),
('Apio', 'Incluye tallos, hojas, semillas y raíces.'),
('Sulfitos', 'Conservantes presentes habitualmente en vinos y vinagres.');

-- 2. RELACIONAR PRODUCTOS CON ALÉRGENOS
-- Usamos subconsultas para que busque el ID por el nombre del plato

-- Gazpacho (Sulfitos por el vinagre)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sulfitos'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Gazpacho Andaluz Tradicional'), 
        'Contiene vinagre de jerez con sulfitos.');

-- Seitán al Horno (Gluten y Soja - El seitán es puro gluten)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Seitán al Horno con Patatas'), 
        'El seitán es proteína de trigo.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Soja'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Seitán al Horno con Patatas'), 
        'Marinado con salsa de soja.');

-- Burger GreenGo (Gluten, Soja y Mostaza)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Pan de brioche artesanal.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Soja'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Proteína vegetal.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Mostaza'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Salsa secreta GreenGo.');

-- Hummus de Remolacha (Sésamo)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sésamo'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Hummus de Remolacha y Edamame'), 
        'Contiene pasta de tahini.');

-- Tarta de Queso Vegana (Frutos de cáscara)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Frutos de cáscara'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Tarta de Queso Vegana'), 
        'Base elaborada con anacardos y dátiles.');

-- Cerveza (Gluten)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Cerveza Artesana Local (Bio)'), 
        'Malta de cebada.');

-- Vino Tinto (Sulfitos)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sulfitos'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Vino Tinto Bio (Copa)'), 
        'Sulfitos naturales de la fermentación.');

-- Esto borra los alérgenos de las hamburguesas que NO son la original
DELETE FROM Alergenos_producto 
WHERE id_producto IN (
    SELECT id_producto 
    FROM Producto 
    WHERE nombre = 'Burger GreenGo Especial' 
    AND id_producto > (
        SELECT MIN(id_producto) 
        FROM Producto 
        WHERE nombre = 'Burger GreenGo Especial'
    )
);

-- Esto borra las filas de la tabla producto que están repetidas
DELETE FROM Producto 
WHERE nombre = 'Burger GreenGo Especial' 
AND id_producto > (
    SELECT MIN(id_producto) 
    FROM Producto 
    WHERE nombre = 'Burger GreenGo Especial'
);

select * from alergenos


CREATE TABLE Pedido (
    id_pedido SERIAL PRIMARY KEY,
    nombre_cliente VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Líneas del ticket (Qué productos van dentro del pedido)
CREATE TABLE Detalles_Pedido (
    id_detalle SERIAL PRIMARY KEY,
    id_pedido INT REFERENCES Pedido(id_pedido) ON DELETE CASCADE,
    id_producto INT REFERENCES PRODUCTO(id_producto),
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL
);

select * from pedido

select * from detalles_pedido

truncate table pedido restart identity


CREATE TABLE Usuarios(
id SERIAL NOT NULL,
usuario VARCHAR(50) NOT NULL,
correo VARCHAR(50) NOT NULL,
contrasena TEXT NOT NULL
)
SELECT * FROM usuarios CREATE TABLE CATEGORIAS (
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

-- RESET DE TODAS LAS IMÁGENES A LOS LINKS ORIGINALES

-- CATEGORÍA 1: PRIMEROS PLATOS
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1594756202469-9ff9799b2e42?q=80&w=500' WHERE nombre = 'Gazpacho Andaluz Tradicional';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?q=80&w=500' WHERE nombre = 'Crema de Calabaza y Jengibre';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=500' WHERE nombre = 'Ensalada César Vegana';

-- CATEGORÍA 2: SEGUNDOS PLATOS
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1476124369491-e7addf5db371?q=80&w=500' WHERE nombre = 'Risotto de Setas Silvestres';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1565299585323-38d6b0865b47?q=80&w=500' WHERE nombre = 'Tacos de Jackfruit al Pastor';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?q=80&w=500' WHERE nombre = 'Seitán al Horno con Patatas';

-- CATEGORÍA 3: POSTRES
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1533134242443-d4fd215305ad?q=80&w=500' WHERE nombre = 'Tarta de Queso Vegana';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1528451634235-900366627581?q=80&w=500' WHERE nombre = 'Mousse de Chocolate y Avellana';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1519996529931-28324d5a630e?q=80&w=500' WHERE nombre = 'Brochetas de Fruta de Temporada';

-- CATEGORÍA 4: BEBIDAS
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1523472721958-978152f4d69b?q=80&w=500' WHERE nombre = 'Limonada Casera con Menta';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?q=80&w=500' WHERE nombre = 'Té Frío de Hibisco';
UPDATE PRODUCTO SET imagen_url = 'https://images.unsplash.com/photo-1535958636474-b021ee887b13?q=80&w=500' WHERE nombre = 'Cerveza Artesana Local (Bio)';

-- 1. Gazpacho (Se ve el cuenco con el tomate y el verde, muy "eco")
UPDATE PRODUCTO 
SET imagen_url = 'https://images.unsplash.com/photo-1547592166-23ac45744acd?q=80&w=500' 
WHERE nombre = 'Gazpacho Andaluz Tradicional';

-- 2. Mousse de Chocolate (Se ve la textura cremosa en el vaso)
UPDATE PRODUCTO 
SET imagen_url = 'https://images.unsplash.com/photo-1541783245831-57d6fb0926d3?q=80&w=500' 
WHERE nombre = 'Mousse de Chocolate y Avellana';


update PRODUCTO
set nombre = 'Bol de Frutas de Temporada'
where nombre = 'Bol de Fruta de Temporada'

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


SELECT p.*, a.*, c.nombre as cat_nombre, i.valor as imp_valor
                FROM PRODUCTO p
                JOIN CATEGORIAS c ON p.id_categoria = c.id_categoria
                JOIN IMPUESTOS i ON p.id_impuesto = i.id_impuesto 
                LEFT JOIN alergenos_producto ap ON p.id_producto = ap.id_producto
				LEFT JOIN alergenos a on ap.id_alergeno = a.id_alergeno 
                ORDER BY p.id_categoria ASC, p.id_producto ASC;


-- AMPLIACIÓN DEL MENÚ GREENGÓ

INSERT INTO PRODUCTO (nombre, descripcion, precio_base, imagen_url, stock, id_categoria, id_impuesto) VALUES 

-- CATEGORÍA 1: PRIMEROS PLATOS
('Hummus de Remolacha y Edamame', 'Trío de hummus caseros servidos con bastoncitos de zanahoria y pan de pita integral.', 6.90, 'https://images.unsplash.com/photo-1577906030551-5b9164f440d8?q=80&w=500', 15, 1, 1),
('Gyozas de Shiitake y Col', '6 unidades de empanadillas japonesas al vapor con salsa de soja cítrica.', 8.20, 'https://images.unsplash.com/photo-1496116218417-1a781b1c416c?q=80&w=500', 12, 1, 1),

-- CATEGORÍA 2: SEGUNDOS PLATOS
('Burger GreenGo Especial', 'Hamburguesa de proteína de guisante, queso vegano, cebolla caramelizada y rúcula.', 14.50, 'https://images.unsplash.com/photo-1525059696034-4767759ad7ba?q=80&w=500', 20, 2, 1),
('Curry Verde de Tofu y Coco', 'Receta tailandesa original con leche de coco, verduras crujientes y arroz basmati.', 13.20, 'https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?q=80&w=500', 10, 2, 1),

-- CATEGORÍA 3: POSTRES
('Brownie de Batata y Nueces', 'Sorprendente brownie sin gluten ni azúcar refinado, servido con helado de coco.', 5.80, 'https://images.unsplash.com/photo-1606312619070-d48b4c652a52?q=80&w=500', 12, 3, 1),
('Pudding de Chía y Mango', 'Capas de semillas de chía hidratadas en leche de almendras con puré de mango fresco.', 4.50, 'https://images.unsplash.com/photo-1590080873978-9312759d6100?q=80&w=500', 18, 3, 1),

-- CATEGORÍA 4: BEBIDAS
('Kombucha de Jengibre y Limón', 'Bebida fermentada artesanal, probiótica y refrescante.', 3.80, 'https://images.unsplash.com/photo-1596464531735-2767c0469b66?q=80&w=500', 30, 4, 2),
('Vino Tinto Bio (Copa)', 'Vino de uva ecológica de viñedos locales sin sulfitos añadidos.', 4.00, 'https://images.unsplash.com/photo-1510812431401-41d2bd2722f3?q=80&w=500', 25, 4, 2);
-- ACTUALIZACIÓN DE IMÁGENES ESPECÍFICAS

-- REPARACIÓN DEFINITIVA DE IMÁGENES (GreenGo)

-- 1. Hummus de Remolacha (Color rosa vibrante, se ve el producto real)
UPDATE PRODUCTO 
SET imagen_url = 'https://th.bing.com/th/id/OIP.RKqDUPurSRPNzaHQFpxu0QHaEK?w=299&h=180&c=7&r=0&o=7&pid=1.7&rm=3' 
WHERE nombre = 'Hummus de Remolacha y Edamame';

-- 2. Burger GreenGo (Una burger vegetal de alta calidad con espinacas/rúcula)
UPDATE PRODUCTO 
SET imagen_url = 'https://i0.wp.com/fitonapp.com/wp-content/uploads/shutterstock_1239647506-scaled.jpg?resize=2048%2C1365&ssl=1' 
WHERE nombre = 'Burger GreenGo Especial';

-- 3. Pudding de Chía y Mango (Se ven perfectamente las semillas y el mango)
UPDATE PRODUCTO 
SET imagen_url = 'https://th.bing.com/th/id/OIP.l1kOiB5Rfh3yiF1hqqhYKQHaFj?w=243&h=182&c=7&r=0&o=7&pid=1.7&rm=3' 
WHERE nombre = 'Pudding de Chía y Mango';

-- 4. Kombucha (Botella artesanal con vaso y burbujas)
UPDATE PRODUCTO 
SET imagen_url = 'https://th.bing.com/th/id/OIP.FtGj-Xp1jCOVAE3MxdNT-AHaEO?w=306&h=180&c=7&r=0&o=7&pid=1.7&rm=3' 
WHERE nombre = 'Kombucha de Jengibre y Limón';


-- 1. INSERTAR ALÉRGENOS COMUNES
INSERT INTO Alergenos (nombre_alergeno, desc_alergeno) VALUES 
('Gluten', 'Presente en cereales como trigo, cebada o centeno.'),
('Frutos de cáscara', 'Incluye almendras, avellanas, nueces, anacardos, etc.'),
('Soja', 'Muy común en productos vegetales como el tofu o tempeh.'),
('Sésamo', 'Semillas de sésamo y productos derivados.'),
('Mostaza', 'Presente en salsas y condimentos.'),
('Apio', 'Incluye tallos, hojas, semillas y raíces.'),
('Sulfitos', 'Conservantes presentes habitualmente en vinos y vinagres.');

-- 2. RELACIONAR PRODUCTOS CON ALÉRGENOS
-- Usamos subconsultas para que busque el ID por el nombre del plato

-- Gazpacho (Sulfitos por el vinagre)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sulfitos'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Gazpacho Andaluz Tradicional'), 
        'Contiene vinagre de jerez con sulfitos.');

-- Seitán al Horno (Gluten y Soja - El seitán es puro gluten)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Seitán al Horno con Patatas'), 
        'El seitán es proteína de trigo.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Soja'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Seitán al Horno con Patatas'), 
        'Marinado con salsa de soja.');

-- Burger GreenGo (Gluten, Soja y Mostaza)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Pan de brioche artesanal.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Soja'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Proteína vegetal.'),
       ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Mostaza'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Burger GreenGo Especial'), 
        'Salsa secreta GreenGo.');

-- Hummus de Remolacha (Sésamo)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sésamo'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Hummus de Remolacha y Edamame'), 
        'Contiene pasta de tahini.');

-- Tarta de Queso Vegana (Frutos de cáscara)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Frutos de cáscara'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Tarta de Queso Vegana'), 
        'Base elaborada con anacardos y dátiles.');

-- Cerveza (Gluten)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Gluten'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Cerveza Artesana Local (Bio)'), 
        'Malta de cebada.');

-- Vino Tinto (Sulfitos)
INSERT INTO Alergenos_producto (id_alergeno, id_producto, info_alergia) 
VALUES ((SELECT id_alergeno FROM Alergenos WHERE nombre_alergeno = 'Sulfitos'), 
        (SELECT id_producto FROM Producto WHERE nombre = 'Vino Tinto Bio (Copa)'), 
        'Sulfitos naturales de la fermentación.');

-- Esto borra los alérgenos de las hamburguesas que NO son la original
DELETE FROM Alergenos_producto 
WHERE id_producto IN (
    SELECT id_producto 
    FROM Producto 
    WHERE nombre = 'Burger GreenGo Especial' 
    AND id_producto > (
        SELECT MIN(id_producto) 
        FROM Producto 
        WHERE nombre = 'Burger GreenGo Especial'
    )
);

-- Esto borra las filas de la tabla producto que están repetidas
DELETE FROM Producto 
WHERE nombre = 'Burger GreenGo Especial' 
AND id_producto > (
    SELECT MIN(id_producto) 
    FROM Producto 
    WHERE nombre = 'Burger GreenGo Especial'
);

select * from alergenos


CREATE TABLE Pedido (
    id_pedido SERIAL PRIMARY KEY,
    nombre_cliente VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Líneas del ticket (Qué productos van dentro del pedido)
CREATE TABLE Detalles_Pedido (
    id_detalle SERIAL PRIMARY KEY,
    id_pedido INT REFERENCES Pedido(id_pedido) ON DELETE CASCADE,
    id_producto INT REFERENCES PRODUCTO(id_producto),
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL
);

select * from pedido

select * from detalles_pedido

truncate table pedido restart identity


CREATE TABLE Usuarios(
id SERIAL NOT NULL,
usuario VARCHAR(50) NOT NULL,
correo VARCHAR(50) NOT NULL,
contrasena TEXT NOT NULL
)
SELECT * FROM usuarios 
