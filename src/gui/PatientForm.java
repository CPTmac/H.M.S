package gui;

import java.awt.*;
import javax.swing.*;
import models.Patient;
import services.AppointmentService;

public class PatientForm extends JDialog {

    public PatientForm(JFrame parent,
                       AppointmentService service) {

        super(parent, "Add New Patient", true);

        setLayout(new GridLayout(4, 2, 10, 10));

        setSize(350, 200);

        setLocationRelativeTo(parent);

        /*
         * =========================
         * Name Field
         * =========================
         */

        add(new JLabel("Patient Name:"));

        JTextField txtName =
                new JTextField();

        add(txtName);

        /*
         * =========================
         * Age Field
         * =========================
         */

        add(new JLabel("Age:"));

        JTextField txtAge =
                new JTextField();

        add(txtAge);

        /*
         * =========================
         * Address Field
         * =========================
         */

        add(new JLabel("Address:"));

        JTextField txtAddress =
                new JTextField();

        add(txtAddress);

        /*
         * =========================
         * Save Button
         * =========================
         */

        JButton btnSave =
                new JButton("Save Patient");

        add(btnSave);

        /*
         * =========================
         * Button Action
         * =========================
         */

        btnSave.addActionListener(e -> {

            try {

                // أخذ البيانات فقط
                String name =
                        txtName.getText();

                int age =
                        Integer.parseInt(
                                txtAge.getText()
                        );

                String address =
                        txtAddress.getText();

                // Validation بسيط
                if (name.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter patient name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                // إنشاء المريض
                Patient patient = new Patient(
                        "1",
                        name,
                        age,
                        address,
                        "P" + (service.getPatients().size() + 1),
                        "None",
                        "None",
                        null
                );

                // إرسال للسيرفس
                service.addPatient(patient);

                // رسالة نجاح
                JOptionPane.showMessageDialog(
                        this,
                        "Patient added successfully!"
                );

                dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be a number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}