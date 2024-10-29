package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL de conexión: Asegúrate de que el puerto y nombre de la base de datos sean correctos
    private static final String URL = "jdbc:mysql://localhost:3306/EduTrack";
    private static final String USER = "root";  // Cambia 'root' si usas otro usuario
    private static final String PASSWORD = "L@auti1234";  //

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
