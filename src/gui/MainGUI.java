package hospital.gui;

import hospital.service.ScheduleService;
import javax.swing.*;

public class MainGUI extends JFrame {
    private ScheduleService service;

    public MainGUI() {
        setTitle("نظام إدارة المستشفى والعيادات");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // إنشاء الـ Service الذي يدير الـ Logic
        service = new ScheduleService();

        // تحميل الـ Dashboard داخل الشاشة الرئيسية
        Dashboard dashboard = new Dashboard(this, service);
        add(dashboard);
    }

    public static void main(String[] args) {
        // تشغيل واجهة الـ GUI في الـ Thread المخصص لها بـ Java
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}
