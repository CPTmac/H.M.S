package gui;

import java.awt.*;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import models.Appointment;
import models.Patient;
import reports.FinancialReport;
import reports.MedicalReport;
import reports.Report;
import reports.RoomReport;
import services.AppointmentService;
import services.BillingService;

public class ReportForm extends JDialog {

    private final AppointmentService appointmentService;
    private final JTextArea reportArea;
    private final JComboBox<String> patientSelector;
    private final JComboBox<String> reportTypeSelector;

    public ReportForm(JFrame parent,
                      AppointmentService appointmentService) {

        super(parent, "Hospital Reports", true);
        this.appointmentService = appointmentService;

        setSize(650, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Hospital Reports Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridLayout(2, 2, 10, 10));
        controls.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        controls.add(new JLabel("Patient:"));
        patientSelector = new JComboBox<>(buildPatientItems());
        controls.add(patientSelector);

        controls.add(new JLabel("Report Type:"));
        reportTypeSelector = new JComboBox<>(new String[]{"Medical", "Financial", "Room"});
        controls.add(reportTypeSelector);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JButton btnShowReport = new JButton("Show Report");
        JButton btnPrintReport = new JButton("Print Report");
        buttonPanel.add(btnShowReport);
        buttonPanel.add(btnPrintReport);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(controls, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        reportArea.setText("Select a patient and report type, then click Show Report.");
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.add(btnClose);
        add(footer, BorderLayout.SOUTH);

        btnShowReport.addActionListener(e -> showReport());
        btnPrintReport.addActionListener(e -> printReport());
    }

    private String[] buildPatientItems() {
        List<Patient> patients = appointmentService.getPatients();
        if (patients.isEmpty()) {
            return new String[]{"No patients available"};
        }

        String[] items = new String[patients.size()];
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            items[i] = patient.getId() + " - " + patient.getName();
        }
        return items;
    }

    private void showReport() {
        String patientItem = (String) patientSelector.getSelectedItem();
        if (patientItem == null || patientItem.isEmpty() || patientItem.equals("No patients available")) {
            JOptionPane.showMessageDialog(this, "Please select a patient.", "Missing Patient", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String patientId = patientItem.split(" - ")[0];
        Patient patient = appointmentService.findPatientById(patientId);
        if (patient == null) {
            JOptionPane.showMessageDialog(this, "Selected patient not found.", "Patient Missing", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String reportType = (String) reportTypeSelector.getSelectedItem();
        Report report;

        switch (reportType) {
            case "Medical" -> {
                List<Appointment> filteredAppointments = new ArrayList<>();
                for (Appointment appointment : appointmentService.getAppointments()) {
                    if (appointment.getPatient() != null && appointment.getPatient().getId().equalsIgnoreCase(patientId)) {
                        filteredAppointments.add(appointment);
                    }
                }
                report = new MedicalReport(patient, filteredAppointments);
            }
            case "Financial" -> {
                var bill = BillingService.getInstance().findBillByPatientId(patientId);
                if (bill == null) {
                    JOptionPane.showMessageDialog(this, "No billing record found for this patient.", "Billing Missing", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                report = new FinancialReport(bill);
            }
            default -> {
                int totalRooms = appointmentService.getAppointments().size();
                report = new RoomReport(totalRooms);
            }
        }

        reportArea.setText(report.generate());
    }

    private void printReport() {
        try {
            boolean complete = reportArea.print();
            if (!complete) {
                JOptionPane.showMessageDialog(this, "Print was cancelled.", "Print Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Could not print report: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
