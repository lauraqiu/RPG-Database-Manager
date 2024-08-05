package ui;

import ui_logic.AdminViewPageDBHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterRaceClassFrame extends JDialog {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JComboBox<String> queryComboBox;
    private JComboBox<String> attributeComboBox;
    private JButton submitButton;
    private CharactersPage parentPage;
    private JTextField numberField;

    public CharacterRaceClassFrame(CharactersPage parentPage, AdminViewPageDBHandler adminViewPageDBHandler) {
        super(parentPage, "Filter Characters by Class and Race with Conditions", true);
        this.adminViewPageDBHandler = adminViewPageDBHandler;
        this.parentPage = parentPage;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel queryLabel = new JLabel("Select query:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(queryLabel, gbc);

        queryComboBox = new JComboBox<>(new String[] {
                "--Select Query--",
                "Average Stats"
        });
        gbc.gridx = 1;
        add(queryComboBox, gbc);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints inputGbc = new GridBagConstraints();
        inputGbc.insets = new Insets(5, 5, 5, 5);

        JLabel attributeLabel = new JLabel("Attribute:");
        inputGbc.gridx = 0;
        inputGbc.gridy = 0;
        inputPanel.add(attributeLabel, inputGbc);

        attributeComboBox = new JComboBox<>(new String[] {
                "STRENGTH", "INTELLIGENCE", "DEXTERITY", "CHARISMA", "LUCK"
        });
        inputGbc.gridx = 1;
        inputPanel.add(attributeComboBox, inputGbc);

        JLabel separatorLabel = new JLabel(" > ");
        inputGbc.gridx = 2;
        inputPanel.add(separatorLabel, inputGbc);

        JLabel numberLabel = new JLabel("Value:");
        inputGbc.gridx = 3;
        inputPanel.add(numberLabel, inputGbc);

        numberField = new JTextField(10);
        inputGbc.gridx = 4;
        inputPanel.add(numberField, inputGbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(inputPanel, gbc);

        submitButton = new JButton("Submit");
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        pack();
        setLocationRelativeTo(parentPage);
    }

    private void handleSubmit() {
        String selectedQuery = (String) queryComboBox.getSelectedItem();
        if ("Group by Race/Class with HAVING".equals(selectedQuery)) {
            try {
                // get user input values
                String attribute = (String) attributeComboBox.getSelectedItem();
                String numberString = numberField.getText();
                int number = Integer.parseInt(numberString);

                // execute query
                ResultSet resultSet = adminViewPageDBHandler.queryRaceClass(attribute, number);
                parentPage.displayResults(resultSet);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format: " + ex.getMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage());
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid query.");
        }
    }
}
