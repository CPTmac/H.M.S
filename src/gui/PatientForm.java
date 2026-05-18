package hospital.gui;

import javax.swing.*;
import java.awt.*;

public class PatientForm extends JDialog {
    public PatientForm(JFrame parent) {
        super(parent, "إضافة مريض جديد", true);
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(350, 200);
        setLocationRelativeTo(parent);

        add(new JLabel(" اسم المريض:"));
        JTextField txtName = new JTextField();
        add(txtName);

        add(new JLabel(" العمر:"));
        JTextField txtAge = new JTextField();
        add(txtAge);

        add(new JLabel(" العنوان:"));
        JTextField txtAddress = new JTextField();
        add(txtAddress);

        JButton btnSave = new JButton("حفظ المريض");
        add(btnSave);

        btnSave.addActionListener(e -> {
            if (!txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "تم حفظ المريض بنجاح! (تمت المحاكاة)");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "برجاء إدخال اسم المريض", "خطأ", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
