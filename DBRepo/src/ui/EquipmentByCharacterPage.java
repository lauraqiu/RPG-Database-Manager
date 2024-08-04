package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;

public class EquipmentByCharacterPage extends JFrame {
    AdminViewPageDBHandler adminViewPageDBHandler;
    public EquipmentByCharacterPage(AdminViewPageDBHandler adminViewPageDBHandler) {
        JLabel textField = new JLabel("Pick a class and get the equipment table for each character in that class");
        textField.setFont(new Font("", Font.PLAIN, 15));
        JComboBox<String> characterComboBox = new JComboBox<>(new String[]{"mage",
                "pirate", "thief", "warrior"});
        JButton searchByCharacterButton = new JButton("Search By Character");
        this.adminViewPageDBHandler = adminViewPageDBHandler;
        setTitle("Equipped Items");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textField, gbc);
        gbc.gridy = 3;
        add(characterComboBox, gbc);
        gbc.gridx = 1;
        add(searchByCharacterButton, gbc);

        setVisible(true);
        searchByCharacterButton.addActionListener(e -> {
            String character = (String) characterComboBox.getSelectedItem();
            adminViewPageDBHandler.getEquipmentByCharacter(character);
        });


    }
}
