package ui;

import model.Materia;
import service.MateriaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarMateriasPanel extends JPanel {

    private final MateriaService materiaService = new MateriaService();
    private JTable materiasTable;
    private DefaultTableModel tableModel;

    public ListarMateriasPanel() {
        setLayout(new BorderLayout());

        // Crear el modelo de la tabla con las columnas adecuadas
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0);
        materiasTable = new JTable(tableModel);
        materiasTable.setFillsViewportHeight(true);

        // Panel de desplazamiento para la tabla
        JScrollPane scrollPane = new JScrollPane(materiasTable);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos en la tabla
        cargarDatos();
    }

    private void cargarDatos() {
        // Limpiar cualquier dato previo en el modelo de la tabla
        tableModel.setRowCount(0);

        // Obtener lista de alumnos del servicio
        List<Materia> materias = materiaService.listarMaterias();

        // Añadir cada alumno como una fila en el modelo de la tabla
        for (Materia materia : materias) {
            tableModel.addRow(new Object[]{
                    materia.getId(),
                    materia.getNombre(),
            });
        }
    }
}
