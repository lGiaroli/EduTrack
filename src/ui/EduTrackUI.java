package ui;

import javax.swing.*;
import java.awt.*;

public class EduTrackUI extends JFrame {
    private JPanel mainPanel;

    public EduTrackUI() {
        setTitle("EduTrack - Gestión de Alumnos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de menú lateral
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        // Botones del menú
        JButton btnAgregarAlumno = new JButton("Agregar Alumno");
        JButton btnAgregarMateria = new JButton("Agregar Materia");
        JButton btnAgregarCalificacion = new JButton("Agregar Calificación");
        JButton btnListarAlumnos = new JButton("Listar Alumnos");
        JButton btnListarMaterias = new JButton("Listar Materias");
        JButton btnListarCalificaciones = new JButton("Listar Calificaciones");
        JButton btnBuscarAlumno = new JButton("Buscar Alumno por ID");
        JButton btnBuscarMateria = new JButton("Buscar Materia por ID");
        JButton btnActualizarAlumno = new JButton("Actualizar Alumno");
        JButton btnActualizarMateria = new JButton("Actualizar Materia");
        JButton btnEliminarAlumno = new JButton("Eliminar Alumno");
        JButton btnEliminarMateria = new JButton("Eliminar Materia");
        JButton btnEliminarCalificacion = new JButton("Eliminar Calificación");

        // Añadir botones al panel
        menuPanel.add(btnAgregarAlumno);
        menuPanel.add(btnAgregarMateria);
        menuPanel.add(btnAgregarCalificacion);
        menuPanel.add(btnListarAlumnos);
        menuPanel.add(btnListarMaterias);
        menuPanel.add(btnListarCalificaciones);
        menuPanel.add(btnBuscarAlumno);
        menuPanel.add(btnBuscarMateria);
        menuPanel.add(btnActualizarAlumno);
        menuPanel.add(btnActualizarMateria);
        menuPanel.add(btnEliminarAlumno);
        menuPanel.add(btnEliminarMateria);
        menuPanel.add(btnEliminarCalificacion);

        add(menuPanel, BorderLayout.WEST);

        // Panel principal para cargar formularios
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Configurar acciones de los botones
        btnAgregarAlumno.addActionListener(e -> mostrarPanel(new AgregarAlumnoPanel()));
        btnAgregarMateria.addActionListener(e -> mostrarPanel(new AgregarMateriaPanel()));
        btnAgregarCalificacion.addActionListener(e -> mostrarPanel(new AgregarCalificacionPanel()));
        btnListarAlumnos.addActionListener(e -> mostrarPanel(new ListarAlumnosPanel()));
        btnListarMaterias.addActionListener(e -> mostrarPanel(new ListarMateriasPanel()));
        btnListarCalificaciones.addActionListener(e -> mostrarPanel(new ListarCalificacionesPanel()));
        btnBuscarAlumno.addActionListener(e -> mostrarPanel(new BuscarAlumnoPanel()));
        btnBuscarMateria.addActionListener(e -> mostrarPanel(new BuscarMateriaPanel()));
        btnActualizarAlumno.addActionListener(e -> mostrarPanel(new ActualizarAlumnoPanel()));
        btnActualizarMateria.addActionListener(e -> mostrarPanel(new ActualizarMateriaPanel()));
        btnEliminarAlumno.addActionListener(e -> mostrarPanel(new EliminarAlumnoPanel()));
        btnEliminarMateria.addActionListener(e -> mostrarPanel(new EliminarMateriaPanel()));
        btnEliminarCalificacion.addActionListener(e -> mostrarPanel(new EliminarCalificacionPanel()));





        // Aquí puedes configurar otros botones para mostrar diferentes paneles, por ejemplo:
        // btnAgregarMateria.addActionListener(e -> mostrarPanel(new AgregarMateriaPanel()));

        setVisible(true);
    }

    // Método para mostrar el panel en el área principal
    private void mostrarPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EduTrackUI::new);
    }
}
