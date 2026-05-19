package gui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import models.Patient;
import services.AppointmentService;
import services.BillingService;

public class MedicationForm extends JDialog {

    public MedicationForm(JFrame parent, AppointmentService appointmentService) {
        super(parent, "Give Medication", true);

        setSize(450, 260);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 8, 8));

        add(new JLabel("Patient:"));
        List<Patient> patients = appointmentService.getPatients();
        String[] patientItems = new String[patients.size()];
        for (int i = 0; i < patients.size(); i++) {
            patientItems[i] = patients.get(i).getId() + " - " + patients.get(i).getName();
        }
        JComboBox<String> comboPatient = new JComboBox<>(patientItems);
        add(comboPatient);

        add(new JLabel("Medication Name:"));
        JTextField txtMedName = new JTextField();
        add(txtMedName);

        add(new JLabel("Type:"));
        JComboBox<String> comboType = new JComboBox<>(new String[]{"Syrups", "Bells", "Shores"});
        add(comboType);

        add(new JLabel("Dosage:"));
        JTextField txtDosage = new JTextField();
        add(txtDosage);

        add(new JLabel("Instructions:"));
        JTextField txtInstructions = new JTextField();
        add(txtInstructions);

        JButton btnApply = new JButton("Apply Medication");
        JButton btnClose = new JButton("Close");

        add(btnApply);
        add(btnClose);

        btnApply.addActionListener(e -> {
            String patientItem = (String) comboPatient.getSelectedItem();
            if (patientItem == null || patientItem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a patient.", "Missing Patient", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String patientId = patientItem.split(" - ")[0];
            String medName = txtMedName.getText().trim();
            String type = (String) comboType.getSelectedItem();

            if (medName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter medication name.", "Missing Medication", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // determine cost by medication type
            double cost = switch (type) {
                case "Syrups" -> 75.0;
                case "Bells" -> 40.0;
                case "Shores" -> 120.0;
                default -> 50.0;
            };

            Patient patient = appointmentService.findPatientById(patientId);
            if (patient == null) {
                JOptionPane.showMessageDialog(this, "Selected patient was not found.", "Patient Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean ok = BillingService.getInstance().addMedicineCostToPatient(patient, cost);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Unable to update billing for this patient.", "Billing Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, String.format("Medication applied. %s (%.2f EGP) added to patient %s", medName, cost, patientId));
            dispose();
        });

        btnClose.addActionListener(e -> dispose());
    }
}
