package co.edu.poli.pyt.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private static final String DB_NAME = obtenerVariable("DB_NAME", "ooodle_game");
    private static final String SERVER_URL = obtenerVariable("DB_SERVER_URL", "jdbc:mysql://localhost:3306/");
    private static final String URL = obtenerVariable("DB_URL", SERVER_URL + DB_NAME);
    private static final String USER = obtenerVariable("DB_USER", "root");
    private static final String PASSWORD = obtenerVariable("DB_PASSWORD", "");

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        crearBaseDeDatosSiNoExiste();
        Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        crearTablasSiNoExisten(conexion);
        return conexion;
    }

    private static void crearBaseDeDatosSiNoExiste() throws SQLException {
        if (!DB_NAME.matches("[A-Za-z0-9_]+")) {
            throw new SQLException("Nombre de base de datos invalido");
        }

        String sql = "CREATE DATABASE IF NOT EXISTS `" + DB_NAME + "`";

        try (Connection conexion = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
                Statement statement = conexion.createStatement()) {
            statement.execute(sql);
        }
    }

    private static void crearTablasSiNoExisten(Connection conexion) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS jugadores (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(80) NOT NULL UNIQUE,
                    puntaje_total INT NOT NULL DEFAULT 0,
                    partidas_ganadas INT NOT NULL DEFAULT 0
                )
                """;

        try (Statement statement = conexion.createStatement()) {
            statement.execute(sql);
        }
    }

    private static String obtenerVariable(String nombre, String valorPorDefecto) {
        String valor = System.getenv(nombre);
        if (valor == null || valor.isBlank()) {
            return valorPorDefecto;
        }
        return valor;
    }
}
