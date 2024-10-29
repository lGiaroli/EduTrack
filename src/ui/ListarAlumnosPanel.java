package ui;

import model.Alumno;
import service.AlumnoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarAlumnosPanel extends JPanel {

    private final AlumnoService alumnoService = new AlumnoService();
    private JTable alumnosTable;
    private DefaultTableModel tableModel;

    public ListarAlumnosPanel() {
        setLayout(new BorderLayout());

        // Crear el modelo de la tabla con las columnas adecuadas
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "Nivel", "Sección"}, 0);
        alumnosTable = new JTable(tableModel);
        alumnosTable.setFillsViewportHeight(true);

        // Panel de desplazamiento para la tabla
        JScrollPane scrollPane = new JScrollPane(alumnosTable);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos en la tabla
        cargarDatos();
    }

    private void cargarDatos() {
        // Limpiar cualquier dato previo en el modelo de la tabla
        tableModel.setRowCount(0);

        // Obtener lista de alumnos del servicio
        List<Alumno> alumnos = alumnoService.listarAlumnos();

        // Añadir cada alumno como una fila en el modelo de la tabla
        for (Alumno alumno : alumnos) {
            tableModel.addRow(new Object[]{
                    alumno.getId(),
                    alumno.getNombre(),
                    alumno.getApellido(),
                    alumno.getNivel(),
                    alumno.getSeccion()
            });
        }
    }
}
