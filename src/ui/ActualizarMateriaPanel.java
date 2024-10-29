package ui;

import model.Materia;
import service.MateriaService;

import javax.swing.*;
import java.awt.*;

public class ActualizarMateriaPanel extends JPanel {

    private final MateriaService materiaService = new MateriaService();

    public ActualizarMateriaPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para buscar por ID
        JLabel lblBuscarId = new JLabel("ID de la Materia:");
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

        // Etiqueta y campo para mostrar y actualizar el nombre de la materia
        JLabel lblNombre = new JLabel("Nombre de la Materia:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblNombre, gbc);

        JTextField txtNombre = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtNombre, gbc);

        // Botón para actualizar la materia
        JButton btnActualizar = new JButton("Actualizar Materia");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnActualizar, gbc);

        // Acción para buscar y cargar los datos de la materia
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtBuscarId.getText());
                Materia materia = materiaService.obtenerMateriaPorId(id);

                if (materia != null) {
                    txtNombre.setText(materia.getNombre());
                } else {
                    JOptionPane.showMessageDialog(this, "Materia no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para actualizar la materia
        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtBuscarId.getText());
                String nombre = txtNombre.getText();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre de la materia no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Materia materiaActualizada = new Materia(id, nombre);
                materiaService.actualizarMateria(materiaActualizada);
                JOptionPane.showMessageDialog(this, "Materia actualizada correctamente.");

                // Limpiar campos después de la actualización
                txtBuscarId.setText("");
                txtNombre.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
