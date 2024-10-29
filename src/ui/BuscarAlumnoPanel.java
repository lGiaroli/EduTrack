package ui;

import model.Alumno;
import service.AlumnoService;

import javax.swing.*;
import java.awt.*;

public class BuscarAlumnoPanel extends JPanel {

    private final AlumnoService alumnoService = new AlumnoService();

    public BuscarAlumnoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta "ID del Alumno"
        JLabel lblAlumnoId = new JLabel("ID del Alumno:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblAlumnoId, gbc);

        // Campo de texto para ingresar el ID del Alumno
        JTextField txtAlumnoId = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(txtAlumnoId, gbc);

        // Botón "Buscar Alumno"
        JButton btnBuscar = new JButton("Buscar Alumno");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa ambas columnas
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnBuscar, gbc);

        // Área para mostrar los detalles del alumno
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

        // Acción del botón "Buscar Alumno"
        btnBuscar.addActionListener(e -> {
            String alumnoIdText = txtAlumnoId.getText();
            if (alumnoIdText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del alumno.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int alumnoId = Integer.parseInt(alumnoIdText);
                Alumno alumno = alumnoService.obtenerAlumnoPorId(alumnoId);

                if (alumno != null) {
                    txtResultado.setText("ID: " + alumno.getId() +
                            "\nNombre: " + alumno.getNombre() +
                            "\nApellido: " + alumno.getApellido() +
                            "\nNivel: " + alumno.getNivel() +
                            "\nSección: " + alumno.getSeccion());
                } else {
                    txtResultado.setText("No se encontró un alumno con el ID: " + alumnoId);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido (número).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
