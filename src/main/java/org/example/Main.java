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
import org.example.model.Producto;
import org.example.service.ProductoService;
import org.example.controller.PedidoController;

public class Main {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);


            server.createContext("/api/productos", new ProductoHandler());
            server.createContext("/api/pedidos", new PedidoHandler());

            server.setExecutor(null);
            server.start();

            DatabaseConfig.getConnection();
            System.out.println("Conexión correcta a PostgreSQL.");
            System.out.println("Servidor GreenGo iniciado en http://localhost:8080");
            System.out.println("Endpoint de productos: http://localhost:8080/api/productos");
            System.out.println("Endpoint de pedidos: http://localhost:8080/api/pedidos");

        } catch (Exception e) {
            System.out.println("Error 503: Servidor no iniciado.");
            e.printStackTrace();
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