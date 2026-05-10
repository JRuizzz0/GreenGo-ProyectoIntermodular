package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.google.gson.Gson;
import org.example.config.DatabaseConfig;
import org.example.dao.UsuarioDAO;
import org.example.model.Usuario;
import org.example.service.UsuarioService;
import org.example.model.Producto;
import org.example.service.ProductoService;
import org.example.controller.PedidoController;

/**
 * Clase principal que inicia el servidor HTTP y configura los servicios de GreenGo.
 *
 * @author Marco Villamediana, Jaime Ruiz y Alejandro Verdugo
 */
public class Main {

    /**
     * Punto de entrada del programa. Configura rutas y arranca el servidor.
     *
     * @param args Argumentos de consola.
     */
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // Configuración de rutas (endpoints)
            server.createContext("/login", new UsuarioHandler());
            server.createContext("/registro", new UsuarioHandler());
            server.createContext("/api/productos", new ProductoHandler());
            server.createContext("/api/pedidos", new PedidoHandler());

            server.setExecutor(null);
            server.start();

            DatabaseConfig.getConnection();
            System.out.println("Conexión correcta a PostgreSQL.");
            System.out.println("Servidor GreenGo iniciado en http://localhost:8080");

        } catch (Exception e) {
            System.out.println("Error 503: Servidor no iniciado.");
            e.printStackTrace();
        }
    }

    /**
     * Manejador para la gestión de usuarios (Login y Registro).
     */
    static class UsuarioHandler implements HttpHandler {
        private UsuarioService service = new UsuarioService();
        private Gson gson = new Gson();

        /**
         * Gestiona peticiones GET y POST para usuarios.
         *
         * @param exchange Objeto de intercambio HTTP.
         * @throws IOException Si ocurre un error de E/S.
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Lógica de manejo de rutas y métodos HTTP
        }

        /**
         * Envía una respuesta HTTP en formato JSON.
         *
         * @param exchange Objeto de intercambio.
         * @param status Código HTTP.
         * @param body Contenido de la respuesta.
         * @throws IOException Si hay error al escribir.
         */
        private void sendResponse(HttpExchange exchange, int status, String body) throws IOException {
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            byte[] bytes = body.getBytes();
            exchange.sendResponseHeaders(status, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

    /**
     * Manejador para la consulta del catálogo de productos.
     */
    static class ProductoHandler implements HttpHandler {
        private ProductoService service = new ProductoService();
        private Gson gson = new Gson();

        /**
         * Responde con la lista de productos en formato JSON.
         *
         * @param exchange Intercambio HTTP.
         * @throws IOException Si hay error de red.
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Lógica de respuesta de catálogo
        }
    }

    /**
     * Manejador para el procesamiento de nuevos pedidos.
     */
    static class PedidoHandler implements HttpHandler {
        private PedidoController pedidoController = new PedidoController();

        /**
         * Recibe un JSON de pedido y lo procesa mediante el controlador.
         *
         * @param exchange Intercambio HTTP.
         * @throws IOException Si hay error al leer o escribir.
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Lógica de recepción de pedidos
        }
    }
}