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


public class Main {


    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/login", new UsuarioHandler());
            server.createContext("/registro", new UsuarioHandler());
            server.createContext("/api/productos", new ProductoHandler());
            server.createContext("/api/pedidos", new PedidoHandler());
            server.setExecutor(null);
            server.start();
            DatabaseConfig.getConnection();
            System.out.println("Conexión correcta a PostgreSQL.");
            System.out.println("Servidor GreenGo iniciado en http://localhost:8080");
            System.out.println("Endpoint de login: http://localhost:8080/login");
            System.out.println("Endpoint de registro: http://localhost:8080/registro");
            System.out.println("Endpoint de productos: http://localhost:8080/api/productos");
            System.out.println("Endpoint de pedidos: http://localhost:8080/api/pedidos");


        } catch (Exception e) {
            System.out.println("Error 503: Servidor no iniciado.");
            e.printStackTrace();
        }
    }




    static class UsuarioHandler implements HttpHandler {
        private UsuarioService service = new UsuarioService();
        private Gson gson = new Gson();


        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            List<Usuario> usuarios = service.obtenerUsuarios();
            String json = gson.toJson(usuarios);
            String method = exchange.getRequestMethod();
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            if (method.equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            try {

                //  GET
                if (method.equalsIgnoreCase("GET")) {

                }

                //  POST (NUEVO)
                else if (method.equalsIgnoreCase("POST")) {

                    if (path.startsWith("/registro")){
                        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                        System.out.println(body);
                        UsuarioDAO usuario = new UsuarioDAO();

                        if (usuario.insertarUsuario(body)) {
                            sendResponse(exchange, 201, "{\"recibido\":\"Usuario registrado correctamente\"}");
                        } else {
                            sendResponse(exchange, 400, "{\"error\":\"Correo o contraseña inválidos\"}");
                        }
                    }
                    else if (path.startsWith("/login")){
                        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                        System.out.println(body);
                        UsuarioDAO usuario = new UsuarioDAO();

                        if (usuario.comprobarUsuario(body)) {
                            sendResponse(exchange, 200, "{\"recibido\":\"¡Bienvenido!\"}");
                        } else {
                            sendResponse(exchange, 401, "{\"recibido\":\"Usuario o contraseña incorrecta\"}");
                        }
                    }
                    else {
                        sendResponse(exchange, 404, "Endpoint POST no válido");
                    }
                }

                else {
                    sendResponse(exchange, 405, "Método no permitido");
                }

            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "Error en Controller");
            }
            sendResponse(exchange, 404, "No encontrada la ruta");
            byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        }
        private void sendResponse (HttpExchange exchange,int status, String body) throws IOException {

            exchange.getResponseHeaders().add("Content-Type", "application/json");

            byte[] bytes = body.getBytes();

            exchange.sendResponseHeaders(status, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

    static class ProductoHandler implements HttpHandler {
        private ProductoService service = new ProductoService();
        private Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");

            if ("GET".equals(exchange.getRequestMethod())) {
                List<Producto> productos = service.obtenerCatalogo();
                String json = gson.toJson(productos);

                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseBytes.length);

                OutputStream os = exchange.getResponseBody();
                os.write(responseBytes);
                os.close();
            }
        }
    }


    static class PedidoHandler implements HttpHandler {
        private PedidoController pedidoController = new PedidoController();

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");


            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }



            if ("POST".equals(exchange.getRequestMethod())) {


                InputStream is = exchange.getRequestBody();
                String jsonBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);


                String jsonResponse = pedidoController.guardarPedido(jsonBody);


                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

                int statusCode = jsonResponse.contains("\"error\"") ? 400 : 200;

                exchange.sendResponseHeaders(statusCode, responseBytes.length);


                OutputStream os = exchange.getResponseBody();
                os.write(responseBytes);
                os.close();

            } else {

                exchange.sendResponseHeaders(405, -1);
            }
        }
    }

}
