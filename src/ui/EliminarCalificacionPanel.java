package ui;

import service.CalificacionService;

import javax.swing.*;
import java.awt.*;

public class EliminarCalificacionPanel extends JPanel {

    private final CalificacionService calificacionService = new CalificacionService();

    public EliminarCalificacionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para ingresar el ID de la calificación a eliminar
        JLabel lblCalificacionId = new JLabel("ID de la Calificación:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblCalificacionId, gbc);

        JTextField txtCalificacionId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(txtCalificacionId, gbc);

        // Botón para eliminar la calificación
        JButton btnEliminar = new JButton("Eliminar Calificación");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnEliminar, gbc);

        // Acción del botón
        btnEliminar.addActionListener(e -> {
            try {
                int calificacionId = Integer.parseInt(txtCalificacionId.getText());

                // Confirmación de eliminación
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de que deseas eliminar la calificación con ID " + calificacionId + "?",
                        "Confirmación de Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    calificacionService.eliminarCalificacion(calificacionId);
                    JOptionPane.showMessageDialog(this, "Calificación eliminada correctamente.");
                    txtCalificacionId.setText(""); // Limpiar campo después de eliminar
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la calificación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
