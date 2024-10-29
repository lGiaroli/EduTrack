package ui;

import service.AlumnoService;

import javax.swing.*;
import java.awt.*;

public class EliminarAlumnoPanel extends JPanel {

    private final AlumnoService alumnoService = new AlumnoService();

    public EliminarAlumnoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para ingresar el ID del alumno a eliminar
        JLabel lblAlumnoId = new JLabel("ID del Alumno:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblAlumnoId, gbc);

        JTextField txtAlumnoId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(txtAlumnoId, gbc);

        // Botón para eliminar al alumno
        JButton btnEliminar = new JButton("Eliminar Alumno");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnEliminar, gbc);

        // Acción del botón
        btnEliminar.addActionListener(e -> {
            try {
                int alumnoId = Integer.parseInt(txtAlumnoId.getText());

                // Confirmación de eliminación
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de que deseas eliminar al alumno con ID " + alumnoId + "?",
                        "Confirmación de Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    alumnoService.eliminarAlumno(alumnoId);
                    JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente.");
                    txtAlumnoId.setText(""); // Limpiar campo después de eliminar
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el alumno: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
