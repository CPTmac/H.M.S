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

        // إعداد شكل الفورم
        setLayout(new GridLayout(5, 2, 10, 10));

        setSize(400, 250);

        setLocationRelativeTo(parent);

        // حقل ID المريض
        add(new JLabel("Patient ID:"));

        JTextField txtPatientId = new JTextField();

        add(txtPatientId);

        // اختيار الدكتور
        add(new JLabel("Select Doctor:"));

        JComboBox<Doctor> comboDoctors = new JComboBox<>();

        // تحميل الدكاترة في الـ ComboBox
        for (Doctor doctor : service.getDoctors()) {

            comboDoctors.addItem(doctor);
        }

        add(comboDoctors);

        // إدخال التاريخ
        add(new JLabel("Date (DD/MM/YYYY):"));

        JTextField txtDate = new JTextField("17/05/2026");

        add(txtDate);

        // إدخال الوقت
        add(new JLabel("Time (HH:MM):"));

        JTextField txtTime = new JTextField("10:00");

        add(txtTime);

        // زر الحجز
        JButton btnBook = new JButton("Confirm Booking");

        add(btnBook);

        // عند الضغط على الزر
        btnBook.addActionListener(e -> {

            // أخذ البيانات فقط من المستخدم
            String appointmentId =
                    "APP" + (service.getAppointments().size() + 1);

            String patientId =
                    txtPatientId.getText();

            Doctor doctor =
                    (Doctor) comboDoctors.getSelectedItem();

            String date =
                    txtDate.getText();

            String time =
                    txtTime.getText();

            // إرسال البيانات للـ Service
            boolean success =
                    service.bookAppointment(
                            appointmentId,
                            patientId,
                            doctor,
                            date,
                            time
                    );

            // عرض النتيجة فقط
            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment booked successfully!"
                );

                // تحديث الجدول
                onAppointmentBooked.run();

                // غلق الفورم
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to book appointment!",
                        "Booking Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}