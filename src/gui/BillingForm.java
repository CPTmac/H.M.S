package gui;

import java.awt.*;
import javax.swing.*;
import models.Billing;
import services.BillingService;

public class BillingForm extends JDialog {

    public BillingForm(JFrame parent) {

        super(parent, "Billing System", true);

        /*
         * =========================
         * Window Settings
         * =========================
         */

        setSize(450, 320);

        setLocationRelativeTo(parent);

        setLayout(new GridLayout(4, 2, 10, 10));

        /*
         * =========================
         * Patient ID
         * =========================
         */

        add(new JLabel("Patient ID:"));

        JTextField txtPatientId =
                new JTextField();

        add(txtPatientId);

        // (Patient Name and Bill Amount inputs removed)

        /*
         * =========================
         * Payment Method
         * =========================
         */

        add(new JLabel("Payment Method:"));

        String[] methods = {
                "Cash",
                "Credit Card",
                "Insurance"
        };

        JComboBox<String> comboPayment =
                new JComboBox<>(methods);

        add(comboPayment);

        /*
         * =========================
         * Buttons
         * =========================
         */

        JButton btnPay =
                new JButton("Process Payment");

        JButton btnShowBill =
                new JButton("Show Bill Data");

        JButton btnClose =
                new JButton("Close");

        add(btnPay);
        add(btnShowBill);
        add(new JLabel());
        add(btnClose);

        /*
         * =========================
         * Actions
         * =========================
         */

        btnPay.addActionListener(e -> {
            String patientId = txtPatientId.getText().trim();
            String paymentMethod = comboPayment.getSelectedItem().toString();

            if (patientId.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the Patient ID before processing payment.",
                        "Billing Data Missing",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boolean paid = BillingService.getInstance().payBill(patientId, paymentMethod);
            if (paid) {
                JOptionPane.showMessageDialog(
                        this,
                        "Payment processed successfully!"
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No bill found for that patient.",
                        "Billing Missing",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        btnShowBill.addActionListener(e -> {
            String patientId = txtPatientId.getText().trim();
            String paymentMethod = comboPayment.getSelectedItem().toString();

            if (patientId.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the Patient ID before displaying the bill.",
                        "Billing Data Missing",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Billing bill = BillingService.getInstance().findBillByPatientId(patientId);
            if (bill == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "No bill found for this patient.",
                        "Billing Missing",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            StringBuilder billSummary = new StringBuilder();
            billSummary.append("===== Bill Data =====\n");
            billSummary.append("Patient ID      : ").append(patientId).append("\n");
            billSummary.append("Patient Name    : ").append(bill.getPatient().getName()).append("\n");
            billSummary.append("Room Cost       : ").append(String.format("%.2f", bill.getRoomCost())).append("\n");
            billSummary.append("Medicine Cost   : ").append(String.format("%.2f", bill.getMedicineCost())).append("\n");
            billSummary.append("Test Cost       : ").append(String.format("%.2f", bill.getTestCost())).append("\n");
            billSummary.append("Total Amount    : ").append(String.format("%.2f", bill.getTotalAmount())).append("\n");
            billSummary.append("Total Due       : ").append(String.format("%.2f", bill.getTotalDue())).append("\n");
            billSummary.append("Payment Method  : ").append(paymentMethod).append("\n");
            billSummary.append("Payment Status  : ").append(bill.isPaid() ? "Paid" : "Unpaid").append("\n");
            billSummary.append("======================");

            JOptionPane.showMessageDialog(
                    this,
                    billSummary.toString(),
                    "Bill Details",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        btnClose.addActionListener(e -> dispose());
    }
}