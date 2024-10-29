package service;

import database.DatabaseConnection;
import model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoService {

    // Método para agregar un alumno y devolver el ID generado
    public int agregarAlumno(Alumno alumno) {
        String sql = "INSERT INTO Alumno (nombre, apellido, nivel, seccion) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setInt(3, alumno.getNivel());
            statement.setString(4, String.valueOf(alumno.getSeccion()));

            statement.executeUpdate();
            System.out.println("Alumno agregado correctamente: " + alumno.getNombre());

            // Obtener el ID generado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Retorna el ID generado
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar alumno: " + e.getMessage());
        }
        return -1; // Retorna -1 si hubo un error
    }

    // Método para obtener un alumno por su ID
    public Alumno obtenerAlumnoPorId(int id) {
        String sql = "SELECT * FROM Alumno WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Alumno(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("nivel"),
                        resultSet.getString("seccion").charAt(0)
                );
            } else {
                System.out.println("No se encontró el alumno con ID: " + id);
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener alumno: " + e.getMessage());
            return null;
        }
    }

    // Método para listar todos los alumnos
    public List<Alumno> listarAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumno";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Alumno alumno = new Alumno(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("nivel"),
                        resultSet.getString("seccion").charAt(0)
                );
                alumnos.add(alumno);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar alumnos: " + e.getMessage());
        }

        return alumnos;
    }

    // Método para actualizar un alumno
    public void actualizarAlumno(Alumno alumno) {
        String sql = "UPDATE Alumno SET nombre = ?, apellido = ?, nivel = ?, seccion = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setInt(3, alumno.getNivel());
            statement.setString(4, String.valueOf(alumno.getSeccion()));
            statement.setInt(5, alumno.getId());

            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Alumno actualizado correctamente: " + alumno.getNombre());
            } else {
                System.out.println("No se encontró el alumno con ID: " + alumno.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar alumno: " + e.getMessage());
        }
    }

    // Método para eliminar un alumno por su ID
    public void eliminarAlumno(int id) {
        String sql = "DELETE FROM Alumno WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Alumno eliminado con ID: " + id);
            } else {
                System.out.println("No se encontró el alumno con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar alumno: " + e.getMessage());
        }
    }

    public List<Alumno> listarAlumnosPorNivelYSeccion(int nivel, char seccion) {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumno WHERE nivel = ? AND seccion = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, nivel);
            statement.setString(2, String.valueOf(seccion));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Alumno alumno = new Alumno(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("nivel"),
                        resultSet.getString("seccion").charAt(0)
                );
                alumnos.add(alumno);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar alumnos por nivel y sección: " + e.getMessage());
        }

        return alumnos;
    }
}
