package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Configuración de la conexión a la base de datos PostgreSQL.
 */
public class DatabaseConfig {

    /** URL de conexión al servidor y base de datos. */
    private static final String URL = "jdbc:postgresql://localhost:5432/GreenGo";

    /** Usuario de acceso. */
    private static final String USERNAME = "postgres";

    /** Contraseña de acceso. */
    private static final String PASSWORD = "postgres";

    /**
     * Establece y devuelve la conexión con la base de datos.
     *
     * @return Objeto connection configurado.
     * @throws SQLException Si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}