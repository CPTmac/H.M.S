package gui;

import java.awt.*;
import javax.swing.*;
import models.MedicalRecord;
import models.Patient;
import services.AppointmentService;

public class PatientForm extends JDialog {

    public PatientForm(JFrame parent,
            AppointmentService service) {

        super(parent, "Add New Patient", true);

        setLayout(new GridLayout(0, 2, 10, 10));
        setSize(350, 400);
        setLocationRelativeTo(parent);
        
        add(new JLabel("Select Gender:"));
        JComboBox<String> comboGenders = new JComboBox<>();
        comboGenders.addItem("Male");
        comboGenders.addItem("Female");
        add(comboGenders);

        // =========================
        // Patient ID   
        // =========================

        add(new JLabel("Patient ID:"));
        JTextField txtId = new JTextField();
        add(txtId);

        // =========================
        // Name
        // =========================
        add(new JLabel("Patient Name:"));
        JTextField txtName = new JTextField();
        add(txtName);

        // =========================
        // Age
        // =========================
        add(new JLabel("Age:"));
        JTextField txtAge = new JTextField();
        add(txtAge);
        // =========================
        // Diagnosis    
        // =========================

        add(new JLabel("Diagnosis:"));
        JTextField txtDiagnosis = new JTextField();
        add(txtDiagnosis);

        // =========================
        // Allergies
        // =========================

        add(new JLabel("Allergies:"));
        JTextField txtAllergies = new JTextField();
        add(txtAllergies);
        // =========================
        // Blood Type (selector)
        // =========================

        add(new JLabel("Blood Type:"));
        JComboBox<String> comboBlood = new JComboBox<>();
        comboBlood.addItem("A+");
        comboBlood.addItem("A-");
        comboBlood.addItem("B+");
        comboBlood.addItem("B-");
        comboBlood.addItem("AB+");
        comboBlood.addItem("AB-");
        comboBlood.addItem("O+");
        comboBlood.addItem("O-");
        add(comboBlood);

        // =========================
        // Save
        // =========================
        JButton btnSave = new JButton("Save Patient");
        add(btnSave);

        btnSave.addActionListener(e -> {

            try {

                String id = txtId.getText();
                String name = txtName.getText();
                String gender = (String) comboGenders.getSelectedItem();
                int age = Integer.parseInt(txtAge.getText());
                String diagnosis = txtDiagnosis.getText();
                String allergies = txtAllergies.getText();
                String bloodType = (String) comboBlood.getSelectedItem();

                Patient patient = new Patient(id,name,age,gender,new MedicalRecord(null, diagnosis, allergies, bloodType));

                service.addPatient(patient);

                JOptionPane.showMessageDialog(this, "Patient added!");

                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number!");
            }
        });
    }
}
