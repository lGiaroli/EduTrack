package service;

import database.DatabaseConnection;
import model.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaService {

    // Método para agregar una materia y devolver el ID generado
    public int agregarMateria(Materia materia) {
        String sql = "INSERT INTO Materia (nombre) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, materia.getNombre());
            statement.executeUpdate();
            System.out.println("Materia agregada correctamente: " + materia.getNombre());

            // Obtener el ID generado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Retorna el ID generado
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar materia: " + e.getMessage());
        }
        return -1; // Retorna -1 si hubo un error
    }

    // Método para obtener una materia por su ID
    public Materia obtenerMateriaPorId(int id) {
        String sql = "SELECT * FROM Materia WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Materia(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                );
            } else {
                System.out.println("No se encontró la materia con ID: " + id);
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener materia: " + e.getMessage());
            return null;
        }
    }

    // Método para listar todas las materias
    public List<Materia> listarMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM Materia";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Materia materia = new Materia(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                );
                materias.add(materia);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar materias: " + e.getMessage());
        }

        return materias;
    }

    // Método para actualizar una materia
    public void actualizarMateria(Materia materia) {
        String sql = "UPDATE Materia SET nombre = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, materia.getNombre());
            statement.setInt(2, materia.getId());

            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Materia actualizada correctamente: " + materia.getNombre());
            } else {
                System.out.println("No se encontró la materia con ID: " + materia.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar materia: " + e.getMessage());
        }
    }

    // Método para eliminar una materia por su ID
    public void eliminarMateria(int id) {
        String sql = "DELETE FROM Materia WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Materia eliminada con ID: " + id);
            } else {
                System.out.println("No se encontró la materia con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar materia: " + e.getMessage());
        }
    }
}
