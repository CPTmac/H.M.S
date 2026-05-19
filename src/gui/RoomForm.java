package gui;

import java.awt.*;
import javax.swing.*;

public class RoomForm extends JFrame {

    private JTextField roomNumberField;
    private JTextField typeField;
    private JTextField capacityField;
    private JButton saveButton;
    private JButton cancelButton;

    private JFrame parent;

    public RoomForm(JFrame parent) {
        this.parent = parent;

        setTitle("Room Form");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        JLabel roomNumberLabel = new JLabel("Room Number:");
        JLabel typeLabel = new JLabel("Type:");
        JLabel capacityLabel = new JLabel("Capacity:");

        roomNumberField = new JTextField();
        typeField = new JTextField();
        capacityField = new JTextField();

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        panel.add(roomNumberLabel);
        panel.add(roomNumberField);

        panel.add(typeLabel);
        panel.add(typeField);

        panel.add(capacityLabel);
        panel.add(capacityField);

        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);

        // actions
        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> saveRoom());
    }

    private void saveRoom() {

        String number = roomNumberField.getText();
        String type = typeField.getText();
        String capacity = capacityField.getText();

        if (number.isEmpty() || type.isEmpty() || capacity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Room Saved:\nNumber: " + number
                + "\nType: " + type
                + "\nCapacity: " + capacity);

        // هنا تقدر تربطه بـ Service أو Database بعدين
    }
}
