package gui;

import java.awt.*;
import javax.swing.*;
import models.Doctor;
import services.AppointmentService;

public class AppointmentForm extends JDialog {

    public AppointmentForm(JFrame parent,
                           AppointmentService service,
                           Runnable onAppointmentBooked) {

        super(parent, "Book New Appointment", true);

        setLayout(new GridLayout(6, 2, 10, 10));
        setSize(450, 400);
        setLocationRelativeTo(parent);

        // =========================
        // Patient ID
        // =========================
        add(new JLabel("Patient ID:"));
        JTextField txtPatientId = new JTextField();
        add(txtPatientId);

        // =========================
        // Doctor
        // =========================
        add(new JLabel("Select Doctor:"));
        JComboBox<Doctor> comboDoctors = new JComboBox<>();

        for (Doctor doctor : service.getDoctors()) {
            comboDoctors.addItem(doctor);
        }

        add(comboDoctors);

        // =========================
        // Date
        // =========================
        add(new JLabel("Date:"));
        JTextField txtDate = new JTextField();
        add(txtDate);

        // =========================
        // Time
        // =========================
        add(new JLabel("Time:"));
        JTextField txtTime = new JTextField();
        add(txtTime);

        // =========================
        // Room
        // =========================
        add(new JLabel("Room:"));
        JTextField txtRoom = new JTextField();
        add(txtRoom);


        // =========================
        // Button
        // =========================
        JButton btnBook = new JButton("Confirm Booking");
        add(btnBook);

        btnBook.addActionListener(e -> {

            String appointmentId =
                    "APP" + (service.getAppointments().size() + 1);

            String patientId = txtPatientId.getText();

            Doctor doctor = (Doctor) comboDoctors.getSelectedItem();

            String date = txtDate.getText();
            String time = txtTime.getText();

            String room = txtRoom.getText();

                boolean success = service.bookAppointment(
                    appointmentId,
                    patientId,
                    doctor,
                    date,
                    time,
                    "",
                    room,
                    ""
                );

            if (success) {

                JOptionPane.showMessageDialog(this, "Appointment booked successfully!");

                onAppointmentBooked.run();

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to book appointment!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}