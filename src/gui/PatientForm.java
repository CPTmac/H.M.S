package gui;

import javax.swing.*;
import java.awt.*;

public class PatientForm extends JDialog {
    public PatientForm(JFrame parent) {
        super(parent, "Add a new Patient", true);
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(350, 200);
        setLocationRelativeTo(parent);

        add(new JLabel("Patient Name:"));
        JTextField txtName = new JTextField();
        add(txtName);

        add(new JLabel("Age:"));
        JTextField txtAge = new JTextField();
        add(txtAge);

        add(new JLabel("Address:"));
        JTextField txtAddress = new JTextField();
        add(txtAddress);

        JButton btnSave = new JButton("Save Patient");
        add(btnSave);

        btnSave.addActionListener(e -> {
            if (!txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient saved successfully! (Simulation completed)");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter the patient's name", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
