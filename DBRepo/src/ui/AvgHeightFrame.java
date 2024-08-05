package ui;

import ui_logic.AdminViewPageDBHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvgHeightFrame extends JDialog {
    private AdminViewPageDBHandler adminViewPageDBHandler;
    private JComboBox<String> optionComboBox;
    private JButton submitButton;
    private CharactersPage parentPage;

    public AvgHeightFrame (CharactersPage parentPage, AdminViewPageDBHandler adminViewPageDBHandler) {
        super(parentPage, "Find Class", true);
        this.adminViewPageDBHandler = adminViewPageDBHandler;
        this.parentPage = parentPage;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel promptLabel = new JLabel("Find class with:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(promptLabel, gbc);

        optionComboBox = new JComboBox<>(new String[] {
                "Max Height",
                "Min Height"
        });
        gbc.gridx = 1;
        add(optionComboBox, gbc);

        submitButton = new JButton("Submit");
        gbc.gridy = 1;
        gbc.gridx = 1;
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
        String selectedOption = (String) optionComboBox.getSelectedItem();

        if (selectedOption != null) {
            try {
                ResultSet resultSet = adminViewPageDBHandler.queryAvgHeight(selectedOption);
                parentPage.displayResults(resultSet);
                JOptionPane.showMessageDialog(this, "Success!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage());
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid option.");
        }
    }
}
