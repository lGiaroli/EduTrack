package ui;

import service.MateriaService;

import javax.swing.*;
import java.awt.*;

public class EliminarMateriaPanel extends JPanel {

    private final MateriaService materiaService = new MateriaService();

    public EliminarMateriaPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para ingresar el ID de la materia a eliminar
        JLabel lblMateriaId = new JLabel("ID de la Materia:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblMateriaId, gbc);

        JTextField txtMateriaId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(txtMateriaId, gbc);

        // Botón para eliminar la materia
        JButton btnEliminar = new JButton("Eliminar Materia");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnEliminar, gbc);

        // Acción del botón
        btnEliminar.addActionListener(e -> {
            try {
                int materiaId = Integer.parseInt(txtMateriaId.getText());

                // Confirmación de eliminación
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de que deseas eliminar la materia con ID " + materiaId + "?",
                        "Confirmación de Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    materiaService.eliminarMateria(materiaId);
                    JOptionPane.showMessageDialog(this, "Materia eliminada correctamente.");
                    txtMateriaId.setText(""); // Limpiar campo después de eliminar
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la materia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
