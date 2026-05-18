package gui;

import models.Appointment;
import services.ScheduleService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Dashboard extends JPanel {
    private ScheduleService service;
    private DefaultTableModel tableModel;

    public Dashboard(JFrame mainFrame, ScheduleService service) {
        this.service = service;
        setLayout(new BorderLayout(10, 10));

        // شريط الأزرار (التحكم)
        JPanel sidebar = new JPanel(new GridLayout(5, 1, 5, 5));
        JButton btnPatients = new JButton("Add Patient");
        JButton btnAppointments = new JButton("Book Appointment");
        JButton btnDoctors = new JButton("Doctors");
        JButton btnReports = new JButton("Reports");
        JButton btnRooms = new JButton("Rooms");
        sidebar.add(btnPatients);
        sidebar.add(btnAppointments);
        sidebar.add(btnDoctors);
        sidebar.add(btnReports);
        sidebar.add(btnRooms);
        add(sidebar, BorderLayout.WEST);

        // جدول عرض المواعيد المحجوزة الحالية
        String[] columns = {"Patient", "Doctor", "Specialization", "Date", "Time"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ربط أزرار الـ GUI بالـ Forms والـ Services
        btnPatients.addActionListener(e -> new PatientForm(mainFrame).setVisible(true));
        
        btnAppointments.addActionListener(e -> {
            new AppointmentForm(mainFrame, service, this::refreshAppointmentTable).setVisible(true);
        });

        btnDoctors.addActionListener(e -> JOptionPane.showMessageDialog(this, "Number of available doctors: " + service.getDoctors().size()));
        btnReports.addActionListener(e -> JOptionPane.showMessageDialog(this, "Report feature will be available soon."));
        btnRooms.addActionListener(e -> JOptionPane.showMessageDialog(this, "Room feature will be available soon."));

        refreshAppointmentTable();
    }

    // تحديث الجدول عند إضافة حجز جديد
    public void refreshAppointmentTable() {
        tableModel.setRowCount(0); // مسح الجدول الحالي
        for (Appointment app : service.getAppointments()) {
            tableModel.addRow(new Object[]{
                app.getPatientName(),
                app.getDoctor().getName(),
                app.getDoctor().getSpecialization(),
                app.getDate(),
                app.getTime()
            });
        }
    }
}
