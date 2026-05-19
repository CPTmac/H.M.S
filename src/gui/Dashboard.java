package gui;

import models.Appointment;
import services.AppointmentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Dashboard extends JPanel {

    private final AppointmentService service;
    private final DefaultTableModel tableModel;

    public Dashboard(JFrame mainFrame,
                     AppointmentService service) {

        this.service = service;

        setLayout(new BorderLayout(10, 10));

        /*
         * =========================
         * Sidebar
         * =========================
         */
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(7, 1, 5, 5));

        JButton btnPatients = new JButton("Add Patient");
        JButton btnDoctors = new JButton("Add Doctor");
        JButton btnAppointments = new JButton("Book Appointment");
        JButton btnBilling = new JButton("Billing");
        JButton btnReports = new JButton("Reports");
        JButton btnRooms = new JButton("Rooms");
        JButton btnTests = new JButton("Lab Tests");

        sidebar.add(btnPatients);
        sidebar.add(btnDoctors);
        sidebar.add(btnAppointments);
        sidebar.add(btnBilling);
        sidebar.add(btnReports);
        sidebar.add(btnRooms);
        sidebar.add(btnTests);

        add(sidebar, BorderLayout.WEST);

        /*
         * =========================
         * Table
         * =========================
         */
        String[] columns = {

                "Patient",
                "Doctor",
                "Specialization",
                "Diagnosis",
                "Room",
                "Lab Tests",
                "Total Due",
                "Date",
                "Time"
        };

        tableModel = new DefaultTableModel(columns, 0);

        JTable table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        /*
         * =========================
         * Actions
         * =========================
         */

        btnPatients.addActionListener(e ->
                new PatientForm(mainFrame, service).setVisible(true)
        );

        btnDoctors.addActionListener(e ->
                new DoctorForm(mainFrame, service).setVisible(true)
        );

        btnAppointments.addActionListener(e ->
                new AppointmentForm(mainFrame, service, this::refreshAppointmentTable).setVisible(true)
        );

        btnBilling.addActionListener(e ->
                new BillingForm(mainFrame).setVisible(true)
        );

        btnReports.addActionListener(e ->
                new ReportForm(mainFrame).setVisible(true)
        );

        btnRooms.addActionListener(e ->
                new RoomForm(mainFrame).setVisible(true)
        );

        btnTests.addActionListener(e ->
                new LabTestForm(mainFrame).setVisible(true)
        );

        /*
         * =========================
         * Load Data
         * =========================
         */
        refreshAppointmentTable();
    }

    /*
     * =========================
     * Refresh Table
     * =========================
     */
    private void refreshAppointmentTable() {

        tableModel.setRowCount(0);

        for (Appointment app : service.getAppointments()) {

            tableModel.addRow(new Object[]{

                    app.getPatient().getName(),
                    app.getDoctor().getName(),
                    app.getDoctor().getSpecialization(),

                    app.getDiagnosis(),
                    app.getRoom(),
                    app.getLabTests(),

                    app.getTotalDue(),

                    app.getDate(),
                    app.getTime()
            });
        }
    }
}