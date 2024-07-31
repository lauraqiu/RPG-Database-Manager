package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EquippedPage extends JFrame {
    private AdminViewPageDBHandler adminViewPageDBHandler;

    public EquippedPage(AdminViewPageDBHandler adminViewPageDBHandler) {
        this.adminViewPageDBHandler = adminViewPageDBHandler;
        setTitle("Equipped Items");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel usernameLabel = new JLabel("Username:");
        JComboBox<String> usernameComboBox = new JComboBox<>(adminViewPageDBHandler.getData("username", "Accounts"));

        topPanel.add(usernameLabel);
        topPanel.add(usernameComboBox);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // to modify Equipped table
        JComboBox<String> equipComboBox = new JComboBox<>(adminViewPageDBHandler.getData("itemName", "Equipments"));
        JComboBox<String> equipCharComboBox = new JComboBox<>(adminViewPageDBHandler.getData("id", "Characters"));
        JPanel equipPanel = createActionPanel("Equip Equipment:", adminViewPageDBHandler.getData("itemName", "Equipments"), adminViewPageDBHandler.getData("name", "Characters"), e -> handleEquipAction(usernameComboBox, equipComboBox, equipCharComboBox));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(equipPanel, gbc);

        JComboBox<String> updateComboBox = new JComboBox<>(adminViewPageDBHandler.getData("itemName", "Equipments"));
        JComboBox<String> updateCharComboBox = new JComboBox<>(adminViewPageDBHandler.getData("id", "Characters"));
        JPanel updatePanel = createActionPanel("Update Equipment:", adminViewPageDBHandler.getData("itemName", "Equipments"), adminViewPageDBHandler.getData("name", "Characters"), e -> handleUpdateAction(usernameComboBox, updateComboBox, updateCharComboBox));
        gbc.gridy = 1;
        centerPanel.add(updatePanel, gbc);

        JComboBox<String> unequipComboBox = new JComboBox<>(adminViewPageDBHandler.getData("itemName", "Equipments"));
        JComboBox<String> unequipCharComboBox = new JComboBox<>(adminViewPageDBHandler.getData("id", "Characters"));
        JPanel unequipPanel = createActionPanel("Unequip Equipment:", adminViewPageDBHandler.getData("itemName", "Equipments"), adminViewPageDBHandler.getData("name", "Characters"), e -> handleUnequipAction(usernameComboBox, unequipComboBox, unequipCharComboBox));
        gbc.gridy = 2;
        centerPanel.add(unequipPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // display Equipped table
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton showTableButton = new JButton("Show Equipped");
        showTableButton.setPreferredSize(new Dimension(150, 30));
        bottomPanel.add(showTableButton);

        add(bottomPanel, BorderLayout.SOUTH);

        showTableButton.addActionListener(e -> showEquippedTable());

        setVisible(true);
    }

    // panel for each action to table
    private JPanel createActionPanel(String title, String[] equipments, String[] characters, ActionListener actionListener) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(title);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Equipment:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> equipmentComboBox = new JComboBox<>(equipments);
        panel.add(equipmentComboBox, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Character:"), gbc);

        gbc.gridx = 3;
        JComboBox<String> characterComboBox = new JComboBox<>(characters);
        panel.add(characterComboBox, gbc);

        gbc.gridx = 4;
        gbc.gridwidth = 1;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(actionListener);
        panel.add(submitButton, gbc);

        return panel;
    }

    private void showEquippedTable() {
        adminViewPageDBHandler.showFullTable("EQUIPMENTS");
    }

    // add based on equipment from equipment table
    private void handleEquipAction(JComboBox<String> usernameComboBox, JComboBox<String> equipComboBox, JComboBox<String> charComboBox) {
        String selectedUsername = (String) usernameComboBox.getSelectedItem();
        String selectedEquip = (String) equipComboBox.getSelectedItem();
        String selectedChar = (String) charComboBox.getSelectedItem();
        adminViewPageDBHandler.equipItem(selectedEquip, selectedChar, selectedUsername);
        JOptionPane.showMessageDialog(this, "Equip action for: " + selectedUsername + "\nEquipment: " + selectedEquip + "\nCharacter: " + selectedChar);
    }

    private void handleUpdateAction(JComboBox<String> usernameComboBox, JComboBox<String> equipComboBox, JComboBox<String> charComboBox) {
        String selectedUsername = (String) usernameComboBox.getSelectedItem();
        String selectedEquip = (String) equipComboBox.getSelectedItem();
        String selectedChar = (String) charComboBox.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Update action for: " + selectedUsername + "\nEquipment: " + selectedEquip + "\nCharacter: " + selectedChar);
    }

    private void handleUnequipAction(JComboBox<String> usernameComboBox, JComboBox<String> equipComboBox, JComboBox<String> charComboBox) {
        String selectedUsername = (String) usernameComboBox.getSelectedItem();
        String selectedEquip = (String) equipComboBox.getSelectedItem();
        String selectedChar = (String) charComboBox.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Unequip action for: " + selectedUsername + "\nEquipment: " + selectedEquip + "\nCharacter: " + selectedChar);
    }
}
