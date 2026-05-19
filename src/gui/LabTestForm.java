package gui;

import javax.swing.*;
import java.awt.*;

public class LabTestForm extends JFrame {

    private JTextField testNameField;
    private JTextField resultField;

    public LabTestForm(JFrame parent) {

        setTitle("Lab Tests");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        init();
    }

    private void init() {

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Test Name:"));
        testNameField = new JTextField();
        add(testNameField);

        add(new JLabel("Result:"));
        resultField = new JTextField();
        add(resultField);

        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        add(save);
        add(cancel);

        cancel.addActionListener(e -> dispose());

        save.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Test Saved");
        });
    }
}