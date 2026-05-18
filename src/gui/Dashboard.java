package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Appointment;
import services.AppointmentService;

public class Dashboard extends JPanel {

    // السيرفس المسؤول عن اللوجيك
    private final AppointmentService service;

    // موديل الجدول
    private final DefaultTableModel tableModel;

    public Dashboard(JFrame mainFrame,
            AppointmentService service) {

        this.service = service;

        // تحديد Layout
        setLayout(new BorderLayout(10, 10));

        /*
         * =========================
         * Sidebar Buttons
         * =========================
         */
        JPanel sidebar
                = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton btnPatients
                = new JButton("Add Patient");

        JButton btnAppointments
                = new JButton("Book Appointment");

        JButton btnDoctors
                = new JButton("Doctors");

        JButton btnReports
                = new JButton("Reports");

        JButton btnRooms
                = new JButton("Rooms");

        sidebar.add(btnPatients);
        sidebar.add(btnAppointments);
        sidebar.add(btnDoctors);
        sidebar.add(btnReports);
        sidebar.add(btnRooms);

        add(sidebar, BorderLayout.WEST);

        /*
         * =========================
         * Appointments Table
         * =========================
         */
        String[] columns = {
            "Patient",
            "Doctor",
            "Specialization",
            "Date",
            "Time"
        };

        tableModel
                = new DefaultTableModel(columns, 0);

        JTable table
                = new JTable(tableModel);

        add(new JScrollPane(table),
                BorderLayout.CENTER);

        /*
         * =========================
         * Button Actions
         * =========================
         */
        // فتح فورم المرضى
        btnPatients.addActionListener(e -> {

            new PatientForm(mainFrame, service)
                    .setVisible(true);
        });
        // فتح فورم الحجز
        btnAppointments.addActionListener(e -> {

            new AppointmentForm(
                    mainFrame,
                    service,
                    this::refreshAppointmentTable
            ).setVisible(true);
        });

        // عرض عدد الدكاترة فقط
        btnDoctors.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Available Doctors: "
                    + service.getDoctors().size()
            );
        });

        // Placeholder للتقارير
        btnReports.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Reports module coming soon."
            );
        });

        // Placeholder للغرف
        btnRooms.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Rooms module coming soon."
            );
        });

        // تحميل البيانات في الجدول
        refreshAppointmentTable();
    }

    /*
     * تحديث جدول المواعيد
     */
    private void refreshAppointmentTable() {

        // مسح البيانات القديمة
        tableModel.setRowCount(0);

        // تحميل المواعيد من السيرفس
        for (Appointment appointment
                : service.getAppointments()) {

            tableModel.addRow(new Object[]{
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                appointment.getDoctor()
                .getSpecialization(),
                appointment.getDate(),
                appointment.getTime()
            });
        }
    }
}
