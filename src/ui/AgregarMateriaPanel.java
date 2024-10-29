package ui;

import model.Materia;
import service.MateriaService;

import javax.swing.*;
import java.awt.*;

public class AgregarMateriaPanel extends JPanel {

    private final MateriaService materiaService = new MateriaService();

    public AgregarMateriaPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado general entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta "Nombre de la Materia"
        JLabel lblNombre = new JLabel("Nombre de la Materia:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblNombre, gbc);

        // Campo de texto para ingresar el nombre de la materia
        JTextField txtNombre = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(txtNombre, gbc);

        // Botón "Agregar Materia"
        JButton btnAgregar = new JButton("Agregar Materia");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa ambas columnas
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAgregar, gbc);

        // Acción del botón
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre de la materia no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Materia materia = new Materia(0, nombre);
            int id = materiaService.agregarMateria(materia);
            JOptionPane.showMessageDialog(this, "Materia agregada con ID: " + id);

            // Limpiar el campo después de agregar
            txtNombre.setText("");
        });
    }
}
