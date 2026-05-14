package co.edu.poli.pyt.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.pyt.model.Jugador;

public class JugadorRepository {

    public Jugador buscarOCrearPorNombre(String nombre) throws SQLException {
        String nombreLimpio = nombre.trim();
        Jugador jugador = buscarPorNombre(nombreLimpio);

        if (jugador != null) {
            return jugador;
        }

        return crear(nombreLimpio);
    }

    public void registrarVictoria(Jugador jugador, int puntosGanados) throws SQLException {
        String sql = """
                UPDATE jugadores
                SET puntaje_total = puntaje_total + ?,
                    partidas_ganadas = partidas_ganadas + 1
                WHERE id = ?
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, puntosGanados);
            statement.setInt(2, jugador.getId());
            statement.executeUpdate();
        }
    }

    public List<Jugador> listarRanking() throws SQLException {
        String sql = """
                SELECT id, nombre, puntaje_total, partidas_ganadas
                FROM jugadores
                ORDER BY puntaje_total DESC, partidas_ganadas DESC, nombre ASC
                """;

        List<Jugador> jugadores = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery()) {
            while (resultado.next()) {
                jugadores.add(mapearJugador(resultado));
            }
        }

        return jugadores;
    }

    private Jugador buscarPorNombre(String nombre) throws SQLException {
        String sql = """
                SELECT id, nombre, puntaje_total, partidas_ganadas
                FROM jugadores
                WHERE nombre = ?
                """;

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    return mapearJugador(resultado);
                }
            }
        }

        return null;
    }

    private Jugador crear(String nombre) throws SQLException {
        String sql = "INSERT INTO jugadores (nombre) VALUES (?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, nombre);
            statement.executeUpdate();

            try (ResultSet claves = statement.getGeneratedKeys()) {
                if (claves.next()) {
                    return new Jugador(claves.getInt(1), nombre);
                }
            }
        }

        throw new SQLException("No se pudo crear el jugador");
    }

    private Jugador mapearJugador(ResultSet resultado) throws SQLException {
        return new Jugador(
                resultado.getInt("id"),
                resultado.getString("nombre"),
                resultado.getInt("puntaje_total"),
                resultado.getInt("partidas_ganadas"));
    }
}
