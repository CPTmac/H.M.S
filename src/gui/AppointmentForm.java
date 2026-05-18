package gui;

import models.Doctor;
import services.ScheduleService;
import javax.swing.*;
import java.awt.*;

public class AppointmentForm extends JDialog {
    public AppointmentForm(JFrame parent, ScheduleService service, Runnable onAppointmentBooked) {
        super(parent, "حجز موعد جديد", true);
        setLayout(new GridLayout(5, 2, 10, 10));
        setSize(400, 250);
        setLocationRelativeTo(parent);

        add(new JLabel(" اسم المريض:"));
        JTextField txtPatient = new JTextField();
        add(txtPatient);

        add(new JLabel(" اختر الدكتور:"));
        JComboBox<Doctor> comboDoctors = new JComboBox<>();
        for (Doctor d : service.getDoctors()) {
            comboDoctors.addItem(d);
        }
        add(comboDoctors);

        add(new JLabel(" التاريخ (DD/MM/YYYY):"));
        JTextField txtDate = new JTextField("17/05/2026");
        add(txtDate);

        add(new JLabel(" الوقت (HH:MM):"));
        JTextField txtTime = new JTextField("10:00");
        add(txtTime);

        JButton btnBook = new JButton("تأكيد الحجز");
        add(btnBook);

        btnBook.addActionListener(e -> {
            String patient = txtPatient.getText();
            Doctor doctor = (Doctor) comboDoctors.getSelectedItem();
            String date = txtDate.getText();
            String time = txtTime.getText();

            boolean success = service.bookAppointment(patient, doctor, date, time);

            if (success) {
                JOptionPane.showMessageDialog(this, "تم الحجز بنجاح!");
                onAppointmentBooked.run(); // تحديث الجدول في الـ Dashboard
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "فشل الحجز! تأكد من البيانات أو تعارض الموعد.", "خطأ تعارض", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
