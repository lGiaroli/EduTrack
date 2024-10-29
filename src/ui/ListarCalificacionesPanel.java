package ui;

import model.Alumno;
import model.Calificacion;
import service.AlumnoService;
import service.CalificacionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarCalificacionesPanel extends JPanel {

    private final CalificacionService calificacionService = new CalificacionService();
    private final AlumnoService alumnoService = new AlumnoService();

    private JComboBox<Integer> cmbNivel;
    private JComboBox<String> cmbSeccion;
    private JComboBox<String> cmbAlumno;
    private JTable calificacionesTable;
    private DefaultTableModel tableModel;

    public ListarCalificacionesPanel() {
        setLayout(new BorderLayout());

        // Panel superior para filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Seleccione Curso y Alumno"));

        // ComboBox para seleccionar el nivel
        cmbNivel = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        filterPanel.add(new JLabel("Nivel:"));
        filterPanel.add(cmbNivel);

        // ComboBox para seleccionar la sección
        cmbSeccion = new JComboBox<>(new String[]{"A", "B"});
        filterPanel.add(new JLabel("Sección:"));
        filterPanel.add(cmbSeccion);

        // ComboBox para seleccionar el alumno
        cmbAlumno = new JComboBox<>();
        filterPanel.add(new JLabel("Alumno:"));
        filterPanel.add(cmbAlumno);

        // Botón para cargar las calificaciones
        JButton btnCargar = new JButton("Cargar Calificaciones");
        filterPanel.add(btnCargar);

        // Agregar el panel de filtros al panel principal
        add(filterPanel, BorderLayout.NORTH);

        // Crear el modelo de la tabla con las columnas adecuadas
        tableModel = new DefaultTableModel(new Object[]{"Nombre Alumno", "Apellido Alumno", "Materia", "Nota", "Fecha"}, 0);
        calificacionesTable = new JTable(tableModel);
        calificacionesTable.setFillsViewportHeight(true);

        // Panel de desplazamiento para la tabla
        JScrollPane scrollPane = new JScrollPane(calificacionesTable);
        add(scrollPane, BorderLayout.CENTER);

        // Acción para actualizar la lista de alumnos cuando se selecciona un curso y sección
        cmbNivel.addActionListener(e -> actualizarListaAlumnos());
        cmbSeccion.addActionListener(e -> actualizarListaAlumnos());

        // Acción del botón para cargar las calificaciones
        btnCargar.addActionListener(e -> cargarCalificaciones());

        // Inicializar la lista de alumnos
        actualizarListaAlumnos();
    }

    private void actualizarListaAlumnos() {
        cmbAlumno.removeAllItems(); // Limpiar la lista de alumnos

        int nivel = (int) cmbNivel.getSelectedItem();
        String seccion = (String) cmbSeccion.getSelectedItem();

        // Agregar opción "Todos" al ComboBox
        cmbAlumno.addItem("Todos");

        // Obtener lista de alumnos del nivel y sección seleccionados
        List<Alumno> alumnos = alumnoService.listarAlumnosPorNivelYSeccion(nivel, seccion.charAt(0));

        // Añadir cada alumno al ComboBox
        for (Alumno alumno : alumnos) {
            cmbAlumno.addItem(alumno.getNombre() + " " + alumno.getApellido() + " (ID: " + alumno.getId() + ")");
        }
    }

    private void cargarCalificaciones() {
        tableModel.setRowCount(0); // Limpiar cualquier dato previo en el modelo de la tabla

        int nivel = (int) cmbNivel.getSelectedItem();
        String seccion = (String) cmbSeccion.getSelectedItem();
        String alumnoSeleccionado = (String) cmbAlumno.getSelectedItem();

        if ("Todos".equals(alumnoSeleccionado)) {
            List<Calificacion> calificaciones = calificacionService.listarCalificacionesPorNivelYSeccion(nivel, seccion.charAt(0));

            for (Calificacion calificacion : calificaciones) {
                tableModel.addRow(new Object[]{
                        calificacion.getNombreAlumno(),
                        calificacion.getApellidoAlumno(),
                        calificacion.getMateriaNombre(),
                        calificacion.getNota(),
                        calificacion.getFecha()
                });
            }
        } else {
            int alumnoId = Integer.parseInt(alumnoSeleccionado.replaceAll("\\D+", ""));
            List<Calificacion> calificaciones = calificacionService.listarCalificacionesPorAlumno(alumnoId);

            for (Calificacion calificacion : calificaciones) {
                tableModel.addRow(new Object[]{
                        calificacion.getNombreAlumno(),
                        calificacion.getApellidoAlumno(),
                        calificacion.getMateriaNombre(),
                        calificacion.getNota(),
                        calificacion.getFecha()
                });
            }
        }
    }
}
