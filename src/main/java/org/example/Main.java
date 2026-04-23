package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.google.gson.Gson;
import org.example.config.DatabaseConfig;

public class Main {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
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


        private Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {



                byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(responseBytes);
                os.close();
            }




                OutputStream os = exchange.getResponseBody();
                os.close();
            }
        }
    }