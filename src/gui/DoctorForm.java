package gui;

import models.Doctor;
import services.AppointmentService;

import javax.swing.*;
import java.awt.*;

public class DoctorForm extends JDialog {

    public DoctorForm(JFrame parent,
                      AppointmentService service) {

        super(parent,
                "Add New Doctor",
                true);

        /*
         * =========================
         * Window Settings
         * =========================
         */

        setSize(400, 300);

        setLocationRelativeTo(parent);

        setLayout(
                new GridLayout(
                        5,
                        2,
                        10,
                        10
                )
        );

        /*
         * =========================
         * Doctor ID
         * =========================
         */

        add(new JLabel("Doctor ID:"));

        JTextField txtId =
                new JTextField();

        add(txtId);

        /*
         * =========================
         * Doctor Name
         * =========================
         */

        add(new JLabel("Doctor Name:"));

        JTextField txtName =
                new JTextField();

        add(txtName);

        /*
         * =========================
         * Doctor Age
         * =========================
         */

        add(new JLabel("Doctor Age:"));

        JTextField txtAge =
                new JTextField();

        add(txtAge);

        /*
         * =========================
         * Specialization
         * =========================
         */

        add(new JLabel("Specialization:"));

        JTextField txtSpecialization =
                new JTextField();

        add(txtSpecialization);

        /*
         * =========================
         * Save Button
         * =========================
         */

        JButton btnSave =
                new JButton("Save Doctor");

        add(btnSave);

        /*
         * =========================
         * Button Action
         * =========================
         */

        btnSave.addActionListener(e -> {

            try {

                // قراءة البيانات
                String id =
                        txtId.getText();

                String name =
                        txtName.getText();

                int age =
                        Integer.parseInt(
                                txtAge.getText()
                        );

                String specialization =
                        txtSpecialization.getText();

                // validation
                if (id.isEmpty()
                        || name.isEmpty()
                        || specialization.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields."
                    );

                    return;
                }

                // إنشاء الدكتور
                Doctor doctor = new Doctor(id,name,age,specialization);

                // إرسال للـ service
                service.addDoctor(doctor);

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor added successfully!"
                );

                dispose();
            }

            catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be a valid number."
                );
            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: "
                                + ex.getMessage()
                );
            }
        });
    }
}