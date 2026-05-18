package gui;

import javax.swing.*;
import services.AppointmentService;

public class MainGUI extends JFrame {

    // السيرفس المسؤول عن اللوجيك
    private final AppointmentService service;

    public MainGUI() {

        /*
         * =========================
         * Window Settings
         * =========================
         */

        setTitle("Hospital Management System");

        setSize(800, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        /*
         * =========================
         * Initialize Services
         * =========================
         */

        service = new AppointmentService();

        /*
         * =========================
         * Load Dashboard
         * =========================
         */

        Dashboard dashboard =
                new Dashboard(this, service);

        add(dashboard);
    }

    public static void main(String[] args) {

        /*
         * تشغيل الـ GUI داخل الـ Event Dispatch Thread
         */

        SwingUtilities.invokeLater(() -> {

            new MainGUI()
                    .setVisible(true);
        });
    }
}