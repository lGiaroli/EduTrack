package ui;

import model.Alumno;
import service.AlumnoService;

import javax.swing.*;
import java.awt.*;

public class ActualizarAlumnoPanel extends JPanel {

    private final AlumnoService alumnoService = new AlumnoService();

    public ActualizarAlumnoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para buscar por ID
        JLabel lblBuscarId = new JLabel("ID del Alumno:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblBuscarId, gbc);

        JTextField txtBuscarId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtBuscarId, gbc);

        JButton btnBuscar = new JButton("Buscar");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnBuscar, gbc);

        // Etiquetas y campos para mostrar y actualizar información del alumno
        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblNombre, gbc);

        JTextField txtNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtNombre, gbc);

        JLabel lblApellido = new JLabel("Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblApellido, gbc);

        JTextField txtApellido = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtApellido, gbc);

        JLabel lblNivel = new JLabel("Nivel (1-6):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblNivel, gbc);

        JComboBox<Integer> cmbNivel = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cmbNivel, gbc);

        JLabel lblSeccion = new JLabel("Sección (A o B):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblSeccion, gbc);

        JComboBox<String> cmbSeccion = new JComboBox<>(new String[]{"A", "B"});
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cmbSeccion, gbc);

        // Botón para actualizar el alumno
        JButton btnActualizar = new JButton("Actualizar Alumno");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnActualizar, gbc);

        // Acción para buscar y cargar los datos del alumno
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtBuscarId.getText());
                Alumno alumno = alumnoService.obtenerAlumnoPorId(id);

                if (alumno != null) {
                    txtNombre.setText(alumno.getNombre());
                    txtApellido.setText(alumno.getApellido());
                    cmbNivel.setSelectedItem(alumno.getNivel());
                    cmbSeccion.setSelectedItem(String.valueOf(alumno.getSeccion()));
                } else {
                    JOptionPane.showMessageDialog(this, "Alumno no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para actualizar el alumno
        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtBuscarId.getText());
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                int nivel = (int) cmbNivel.getSelectedItem();
                char seccion = ((String) cmbSeccion.getSelectedItem()).charAt(0);

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Alumno alumnoActualizado = new Alumno(id, nombre, apellido, nivel, seccion);
                alumnoService.actualizarAlumno(alumnoActualizado);
                JOptionPane.showMessageDialog(this, "Alumno actualizado correctamente.");

                // Limpiar campos después de la actualización
                txtBuscarId.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                cmbNivel.setSelectedIndex(0);
                cmbSeccion.setSelectedIndex(0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
