package gui;

import javax.swing.*;
import java.awt.*;

public class ReportForm extends JDialog {

    public ReportForm(JFrame parent) {

        super(parent, "Hospital Reports", true);

        /*
         * =========================
         * Window Settings
         * =========================
         */

        setSize(500, 350);

        setLocationRelativeTo(parent);

        setLayout(new BorderLayout(10, 10));

        /*
         * =========================
         * Title
         * =========================
         */

        JLabel title =
                new JLabel(
                        "Hospital Reports Dashboard",
                        SwingConstants.CENTER
                );

        title.setFont(new Font("Arial",
                Font.BOLD,
                20));

        add(title, BorderLayout.NORTH);

        /*
         * =========================
         * Reports Area
         * =========================
         */

        JTextArea reportArea =
                new JTextArea();

        reportArea.setEditable(false);

        reportArea.setText(
                """
                ===== Available Reports =====
                
                • Patient Admissions Report
                
                • Doctor Appointments Report
                
                • Medication Prescriptions Report
                
                • Laboratory Test Results
                
                • Room Occupancy Report
                
                • Financial Performance Report
                
                
                Reports module is currently under development.
                """
        );

        add(new JScrollPane(reportArea),
                BorderLayout.CENTER);

        /*
         * =========================
         * Close Button
         * =========================
         */

        JButton btnClose =
                new JButton("Close");

        btnClose.addActionListener(e -> dispose());

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(btnClose);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}