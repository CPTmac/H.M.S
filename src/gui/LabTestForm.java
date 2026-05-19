package gui;

import exceptions.LabProblemException;
import java.awt.*;
import javax.swing.*;
import models.LaboratoryCatalog;
import services.AppointmentService;
import services.LaboratoryService;

public class LabTestForm extends JFrame {

    private JComboBox<String> patientSelector;
    private JComboBox<String> testSelector;
    private JLabel priceValueLabel;
    private JTextField resultField;

    private final AppointmentService appointmentService;
    private final LaboratoryService laboratoryService;

    public LabTestForm(JFrame parent, AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
        this.laboratoryService = new LaboratoryService();

        setTitle("Lab Tests");
        setSize(550, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        init();
    }

    private void init() {

        setLayout(new GridLayout(5, 2, 10, 10));

        // build patient selector from appointment service
        add(new JLabel("Patient:"));
        java.util.List<models.Patient> patients = appointmentService.getPatients();
        String[] patientItems = new String[patients.size()];
        for (int i = 0; i < patients.size(); i++) {
            patientItems[i] = patients.get(i).getId() + " - " + patients.get(i).getName();
        }
        patientSelector = new JComboBox<>(patientItems);
        add(patientSelector);

        // build test selector
        String[] availableTests = new String[LaboratoryCatalog.AVAILABLE_TESTS_AND_PRICES.length];
        for (int i = 0; i < availableTests.length; i++) {
            availableTests[i] = LaboratoryCatalog.AVAILABLE_TESTS_AND_PRICES[i][0] + " [" + LaboratoryCatalog.AVAILABLE_TESTS_AND_PRICES[i][1] + "]";
        }

        add(new JLabel("Lab Test:"));
        testSelector = new JComboBox<>(availableTests);
        add(testSelector);

        add(new JLabel("Price:"));
        priceValueLabel = new JLabel();
        add(priceValueLabel);

        add(new JLabel("Result:"));
        resultField = new JTextField();
        add(resultField);

        JButton btnShowTests = new JButton("Show Available Tests");
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        add(btnShowTests);
        add(save);
        add(new JLabel());
        add(cancel);

        cancel.addActionListener(e -> dispose());

        btnShowTests.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                LaboratoryCatalog.buildMenuText(),
                "Available Lab Tests",
                JOptionPane.INFORMATION_MESSAGE
        ));

        testSelector.addActionListener(e -> updatePriceLabel());
        updatePriceLabel();

        save.addActionListener(e -> {
            String patientItem = (String) patientSelector.getSelectedItem();
            String selectedTest = (String) testSelector.getSelectedItem();
            String result = resultField.getText().trim();

            if (patientItem == null || patientItem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.", "Missing Patient", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (selectedTest == null || selectedTest.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please choose a lab test before saving.", "Missing Test", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String patientId = patientItem.split(" - ")[0];

            try {
                // selectedTest is like "Name [ABBR]" — extract the name part for lookup
                int br = selectedTest.indexOf(" [");
                String namePart = (br > 0) ? selectedTest.substring(0, br) : selectedTest;
                double price = LaboratoryCatalog.lookupPriceByKeyword(namePart);

                // create the lab test and the billing update is handled inside LaboratoryService
                laboratoryService.createLabTest(patientId, "", namePart, price);

                JOptionPane.showMessageDialog(this,
                        String.format("Test saved successfully.\nPatient: %s\nTest: %s\nResult: %s\nCost: %.2f EGP",
                                patientItem,
                                namePart,
                                result.isEmpty() ? "N/A" : result,
                                price),
                        "Lab Test Saved",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (LabProblemException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lab Problem", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updatePriceLabel() {
        String selectedTest = (String) testSelector.getSelectedItem();
        try {
            double price = LaboratoryCatalog.lookupPriceByKeyword(selectedTest);
            priceValueLabel.setText(String.format("%.2f EGP", price));
        } catch (LabProblemException ex) {
            priceValueLabel.setText("Price unavailable");
        }
    }
}
