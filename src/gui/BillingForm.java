package gui;

import java.awt.*;
import javax.swing.*;

public class BillingForm extends JDialog {

    public BillingForm(JFrame parent) {

        super(parent, "Billing System", true);

        /*
         * =========================
         * Window Settings
         * =========================
         */

        setSize(450, 300);

        setLocationRelativeTo(parent);

        setLayout(new GridLayout(5, 2, 10, 10));

        /*
         * =========================
         * Patient ID
         * =========================
         */

        add(new JLabel("Patient ID:"));

        JTextField txtPatientId =
                new JTextField();

        add(txtPatientId);

        /*
         * =========================
         * Patient Name
         * =========================
         */

        add(new JLabel("Patient Name:"));

        JTextField txtPatientName =
                new JTextField();

        add(txtPatientName);

        /*
         * =========================
         * Amount
         * =========================
         */

        add(new JLabel("Bill Amount:"));

        JTextField txtAmount =
                new JTextField();

        add(txtAmount);

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

        JButton btnClose =
                new JButton("Close");

        add(btnPay);

        add(btnClose);

        /*
         * =========================
         * Actions
         * =========================
         */

        btnPay.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment processed successfully!"
            );
        });

        btnClose.addActionListener(e -> dispose());
    }
}