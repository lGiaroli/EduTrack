package ui;

import model.Alumno;
import service.AlumnoService;

import javax.swing.*;
import java.awt.*;

public class AgregarAlumnoPanel extends JPanel {

    private final AlumnoService alumnoService = new AlumnoService();

    public AgregarAlumnoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo de "Nombre"
        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        add(lblNombre, gbc);

        JTextField txtNombre = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1; // Permitir expansión horizontal
        add(txtNombre, gbc);

        // Etiqueta y campo de "Apellido"
        JLabel lblApellido = new JLabel("Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblApellido, gbc);

        JTextField txtApellido = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        add(txtApellido, gbc);

        // Etiqueta y campo de "Nivel"
        JLabel lblNivel = new JLabel("Nivel (1-6):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblNivel, gbc);

        JComboBox<Integer> cmbNivel = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        add(cmbNivel, gbc);

        // Etiqueta y campo de "Sección"
        JLabel lblSeccion = new JLabel("Sección (A o B):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblSeccion, gbc);

        JComboBox<String> cmbSeccion = new JComboBox<>(new String[]{"A", "B"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        add(cmbSeccion, gbc);

        // Botón "Agregar Alumno"
        JButton btnAgregar = new JButton("Agregar Alumno");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAgregar, gbc);

        // Acción del botón
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int nivel = (int) cmbNivel.getSelectedItem();
            char seccion = ((String) cmbSeccion.getSelectedItem()).charAt(0);

            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Alumno alumno = new Alumno(0, nombre, apellido, nivel, seccion);
            int id = alumnoService.agregarAlumno(alumno);
            JOptionPane.showMessageDialog(this, "Alumno agregado con ID: " + id);

            // Limpiar campos después de agregar
            txtNombre.setText("");
            txtApellido.setText("");
        });
    }
}