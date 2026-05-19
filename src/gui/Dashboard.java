package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Appointment;
import services.AppointmentService;
import services.BillingService;

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
        sidebar.setLayout(new GridLayout(8, 1, 5, 5));

        JButton btnPatients = new JButton("Add Patient");
        JButton btnDoctors = new JButton("Add Doctor");
        JButton btnAppointments = new JButton("Book Appointment");
        JButton btnBilling = new JButton("Billing");
        JButton btnReports = new JButton("Reports");
        JButton btnRooms = new JButton("Rooms");
        JButton btnTests = new JButton("Lab Tests");
        JButton btnMedications = new JButton("Medications");

        sidebar.add(btnPatients);
        sidebar.add(btnDoctors);
        sidebar.add(btnAppointments);
        sidebar.add(btnBilling);
        sidebar.add(btnReports);
        sidebar.add(btnRooms);
        sidebar.add(btnTests);
        sidebar.add(btnMedications);

        add(sidebar, BorderLayout.WEST);

        /*
         * =========================
         * Table
         * =========================
         */
        String[] columns = {

                "Patient ID",
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
                new ReportForm(mainFrame, service).setVisible(true)
        );

        btnRooms.addActionListener(e ->
                new RoomForm(mainFrame).setVisible(true)
        );

        btnTests.addActionListener(e ->
                new LabTestForm(mainFrame, service).setVisible(true)
        );

        btnMedications.addActionListener(e ->
                new MedicationForm(mainFrame, service).setVisible(true)
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

            double due = 0.0;
            if (app.getPatient() != null) {
                due = BillingService.getInstance().getTotalDueByPatientId(app.getPatient().getId());
            }

            tableModel.addRow(new Object[]{

                    app.getPatient().getId(),
                    app.getPatient().getName(),
                    app.getDoctor().getName(),
                    app.getDoctor().getSpecialization(),

                    app.getDiagnosis(),
                    app.getRoom(),
                    app.getLabTests(),

                    due,

                    app.getDate(),
                    app.getTime()
            });
        }
    }
}