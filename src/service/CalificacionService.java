package service;

import database.DatabaseConnection;
import model.Calificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalificacionService {

    // Método para agregar una calificación a la base de datos
    public void agregarCalificacion(Calificacion calificacion) {
        String sql = "INSERT INTO Calificacion (alumno_id, materia_id, nota, fecha) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, calificacion.getAlumnoId());
            statement.setInt(2, calificacion.getMateriaId());
            statement.setDouble(3, calificacion.getNota());
            statement.setDate(4, new java.sql.Date(calificacion.getFecha().getTime()));

            statement.executeUpdate();
            System.out.println("Calificación agregada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar calificación: " + e.getMessage());
        }
    }

    // Método para listar calificaciones según el nivel y la sección de los alumnos
    public List<Calificacion> listarCalificacionesPorNivelYSeccion(int nivel, char seccion) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT c.id, c.alumno_id, c.materia_id, c.nota, c.fecha, m.nombre AS materia_nombre, " +
                "a.nombre AS nombre_alumno, a.apellido AS apellido_alumno " +
                "FROM Calificacion c " +
                "JOIN Alumno a ON c.alumno_id = a.id " +
                "JOIN Materia m ON c.materia_id = m.id " +
                "WHERE a.nivel = ? AND a.seccion = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, nivel);
            statement.setString(2, String.valueOf(seccion));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Calificacion calificacion = new Calificacion(
                        resultSet.getInt("id"),
                        resultSet.getInt("alumno_id"),
                        resultSet.getInt("materia_id"),
                        resultSet.getDouble("nota"),
                        resultSet.getDate("fecha"),
                        resultSet.getString("materia_nombre"),
                        resultSet.getString("nombre_alumno"),
                        resultSet.getString("apellido_alumno")
                );
                calificaciones.add(calificacion);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar calificaciones por nivel y sección: " + e.getMessage());
        }

        return calificaciones;
    }

    public void eliminarCalificacion(int id) {
        String sql = "DELETE FROM Calificacion WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Calificación eliminada con ID: " + id);
            } else {
                System.out.println("No se encontró la calificación con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar calificación: " + e.getMessage());
        }
    }

    public List<Calificacion> listarCalificacionesPorAlumno(int alumnoId) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT c.id, c.alumno_id, c.materia_id, c.nota, c.fecha, m.nombre AS materia_nombre, " +
                "a.nombre AS nombre_alumno, a.apellido AS apellido_alumno " +
                "FROM Calificacion c " +
                "JOIN Materia m ON c.materia_id = m.id " +
                "JOIN Alumno a ON c.alumno_id = a.id " +
                "WHERE c.alumno_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, alumnoId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Calificacion calificacion = new Calificacion(
                        resultSet.getInt("id"),
                        resultSet.getInt("alumno_id"),
                        resultSet.getInt("materia_id"),
                        resultSet.getDouble("nota"),
                        resultSet.getDate("fecha"),
                        resultSet.getString("materia_nombre"),
                        resultSet.getString("nombre_alumno"),
                        resultSet.getString("apellido_alumno")
                );
                calificaciones.add(calificacion);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar calificaciones por alumno: " + e.getMessage());
        }

        return calificaciones;
    }
}
