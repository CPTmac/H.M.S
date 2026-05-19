package gui;

import javax.swing.*;
import services.AppointmentService;

public class MainGUI extends JFrame {

    // السيرفس المسؤول عن إدارة البرنامج كله
    private final AppointmentService service;

    public MainGUI() {

        /*
         * =========================
         * Window Settings
         * =========================
         */

        setTitle("Hospital Management System");

        setSize(1000, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        /*
         * =========================
         * Initialize Service
         * =========================
         */

        service = new AppointmentService();

        /*
         * =========================
         * Dashboard
         * =========================
         */

        Dashboard dashboard =
                new Dashboard(
                        this,
                        service
                );

        add(dashboard);
    }

    /*
     * =========================
     * Main Method
     * =========================
     */

    public static void main(String[] args) {

        /*
         * تشغيل الـ GUI داخل Thread آمن لـ Swing
         */

        SwingUtilities.invokeLater(() -> {

            try {

                /*
                 * إنشاء وتشغيل البرنامج
                 */

                MainGUI gui =
                        new MainGUI();

                gui.setVisible(true);

            }

            catch (Exception e) {

                /*
                 * طباعة أي مشكلة أثناء التشغيل
                 */

                System.out.println(
                        "System Error: "
                                + e.getMessage()
                );
            }
        });
    }
}