package ui;

import model.Materia;
import service.MateriaService;

import javax.swing.*;
import java.awt.*;

public class BuscarMateriaPanel extends JPanel {

    private final MateriaService materiaService = new MateriaService();

    public BuscarMateriaPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta "ID de la Materia"
        JLabel lblMateriaId = new JLabel("ID de la Materia:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblMateriaId, gbc);

        // Campo de texto para ingresar el ID de la Materia
        JTextField txtMateriaId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(txtMateriaId, gbc);

        // Botón "Buscar Materia"
        JButton btnBuscar = new JButton("Buscar Materia");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa ambas columnas
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnBuscar, gbc);

        // Área para mostrar los detalles de la materia
        JTextArea txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Acción del botón "Buscar Materia"
        btnBuscar.addActionListener(e -> {
            String materiaIdText = txtMateriaId.getText();
            if (materiaIdText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la materia.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int materiaId = Integer.parseInt(materiaIdText);
                Materia materia = materiaService.obtenerMateriaPorId(materiaId);

                if (materia != null) {
                    txtResultado.setText("ID: " + materia.getId() +
                            "\nNombre: " + materia.getNombre());
                } else {
                    txtResultado.setText("No se encontró una materia con el ID: " + materiaId);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido (número).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

