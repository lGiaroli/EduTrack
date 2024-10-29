package ui;

import model.Alumno;
import model.Calificacion;
import model.Materia;
import service.AlumnoService;
import service.CalificacionService;
import service.MateriaService;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class AgregarCalificacionPanel extends JPanel {

    private final CalificacionService calificacionService = new CalificacionService();
    private final AlumnoService alumnoService = new AlumnoService();
    private final MateriaService materiaService = new MateriaService();

    public AgregarCalificacionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta "Alumno"
        JLabel lblAlumno = new JLabel("Alumno:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblAlumno, gbc);

        // ComboBox para seleccionar el alumno
        JComboBox<Alumno> cmbAlumno = new JComboBox<>(cargarAlumnos());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(cmbAlumno, gbc);

        // Etiqueta "Materia"
        JLabel lblMateria = new JLabel("Materia:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblMateria, gbc);

        // ComboBox para seleccionar la materia
        JComboBox<Materia> cmbMateria = new JComboBox<>(cargarMaterias());
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(cmbMateria, gbc);

        // Etiqueta "Nota"
        JLabel lblNota = new JLabel("Nota:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblNota, gbc);

        // Campo de texto para ingresar la Nota
        JTextField txtNota = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtNota, gbc);

        // Botón "Agregar Calificación"
        JButton btnAgregar = new JButton("Agregar Calificación");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa ambas columnas
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAgregar, gbc);

        // Acción del botón
        btnAgregar.addActionListener(e -> {
            try {
                Alumno alumno = (Alumno) cmbAlumno.getSelectedItem();
                Materia materia = (Materia) cmbMateria.getSelectedItem();
                double nota = Double.parseDouble(txtNota.getText());

                if (nota < 0 || nota > 10) {
                    JOptionPane.showMessageDialog(this, "La nota debe estar entre 0 y 10.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear una nueva calificación con la fecha actual
                Calificacion calificacion = new Calificacion(
                        0,
                        alumno.getId(),
                        materia.getId(),
                        nota,
                        new Date(),
                        materia.getNombre(),
                        alumno.getNombre(),
                        alumno.getApellido()
                );
                calificacionService.agregarCalificacion(calificacion);

                JOptionPane.showMessageDialog(this, "Calificación agregada correctamente.");

                // Limpiar los campos después de agregar
                txtNota.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una nota válida.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Error: Selección de alumno o materia inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Método para cargar los alumnos en el ComboBox
    private Alumno[] cargarAlumnos() {
        List<Alumno> alumnos = alumnoService.listarAlumnos();
        return alumnos.toArray(new Alumno[0]);
    }

    // Método para cargar las materias en el ComboBox
    private Materia[] cargarMaterias() {
        List<Materia> materias = materiaService.listarMaterias();
        return materias.toArray(new Materia[0]);
    }
}
